package hh.demo.presentation.lecture;

import hh.demo.domain.enrollment.EnrollmentStatus;
import hh.demo.presentation.dto.request.EnrollLectureReq;
import hh.demo.presentation.dto.request.LectureReq;
import hh.demo.presentation.dto.response.EnrollLectureRes;
import hh.demo.domain.enrollment.infra.EnrollmentService;
import hh.demo.domain.lecture.infra.LectureService;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/lecture/{lectureId}")
public class LectureController {

    private final EnrollmentService enrollmentService;
    private final LectureService lectureService;

    public LectureController(EnrollmentService enrollmentService, LectureService lectureService) {
        this.enrollmentService = enrollmentService;
        this.lectureService = lectureService;
    }


    // 특강 신청
    @PostMapping
    @Retryable(
            value = {CannotAcquireLockException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public void enrollLecture(@PathVariable String lectureId, @RequestBody EnrollLectureReq req) {
        String userId = req.getUserId();
        LectureReq lectureReq = lectureService.getLectureDtoByLectureId(lectureId);
        if (lectureReq.getEnrollmentMax() <= lectureReq.getEnrollmentNumber()) {
            throw new RuntimeException("정원 초과되었습니다.");
        }

        // 등록증 처리
        enrollmentService.saveEnrollmentStatus(lectureId, req.getUserId(), EnrollmentStatus.ENROLLING);

        try {
            lectureService.updateLectureEnrollment(lectureId);
        } catch (Exception e) {
            // 등록 실패 처리
            enrollmentService.updateEnrollmentStatus(lectureId, userId, EnrollmentStatus.ENROLL_FAIL);
            throw e;
        }
        // 등록 성공 처리
        enrollmentService.updateEnrollmentStatus(lectureId, userId, EnrollmentStatus.ENROLL_SUCCESS);
    }


    // 특강 등록 실패
    @Recover
    public void removeEnrollment(CannotAcquireLockException e, String lectureId, EnrollLectureReq req) {
        enrollmentService.deleteEnrollment(lectureId, req.getUserId());
        throw new RuntimeException("특강 신청이 실패하였습니다.");
    }


    // 1. 특  조회
    @GetMapping
    public EnrollLectureRes getLectureEnrollmentResult(@PathVariable String lectureId, @RequestParam String userId) {
        boolean enrollmentResult = enrollmentService.existEnrollByLectureIdAndUserId(lectureId, userId);
        return new EnrollLectureRes(lectureId, userId, enrollmentResult);
    }
}
