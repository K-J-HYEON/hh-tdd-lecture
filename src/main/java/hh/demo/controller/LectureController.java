package hh.demo.controller;

import hh.demo.domain.EnrollmentStatus;
import hh.demo.dto.request.EnrollLectureReq;
import hh.demo.dto.request.LectureDto;
import hh.demo.dto.response.EnrollLectureRes;
import hh.demo.service.EnrollmentService;
import hh.demo.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.web.bind.annotation.*;
import org.springframework.retry.annotation.Recover;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture/{lectureId}")
public class LectureController {

    private final EnrollmentService enrollmentService;
    private final LectureService lectureService;

    public LectureController(EnrollmentService enrollmentService, LectureService lectureService) {
        this.enrollmentService = enrollmentService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public void enrollLecture(@PathVariable String lectureId, @RequestBody EnrollLectureReq req) {
        String userId = req.getUserId();
        LectureDto lectureDto = lectureService.getLectureDtoByLectureId(lectureId);
        if (lectureDto.getMaxEnrollment() <= lectureDto.getCurrentEnrollment()) {
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

    @Recover
    public void removeEnrollment(CannotAcquireLockException e, String lectureId, EnrollLectureReq req) {
        enrollmentService.deleteEnrollment(lectureId, req.getUserId());
        throw new RuntimeException("특강 신청이 실패하였습니다.")
    }


    // 1. lecture 조회
    @GetMapping
    public EnrollLectureRes getLectureEnrollmentResult(@PathVariable String lectureId, @RequestParam String userId) {
        boolean enrollmentResult = enrollmentService.existEnrollByLectureIdAndUserId(lectureId, userId);
        return new EnrollLectureRes(lectureId, userId, enrollmentResult);
    }
}
