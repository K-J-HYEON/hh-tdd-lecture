package hh.demo.controller;

import hh.demo.dto.request.EnrollLectureReq;
import hh.demo.dto.response.EnrollLectureRes;
import hh.demo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    private final EnrollmentService enrollmentService;

    public LectureController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    @PostMapping("{lectureId}")
    public void enrollLecture(@RequestBody EnrollLectureReq req) {
        // 1. lecture 조회
        // 2. 동일한 신청자 중복요청 확인
    }

    @GetMapping
    public EnrollLectureRes getLectureEnrollmentResult(@PathVariable String lectureId, @RequestParam String userId) {
        boolean enrollmentResult = enrollmentService.existEnrollByLectureIdAndUserId(lectureId, userId);
        return new EnrollLectureRes(lectureId, userId, enrollmentResult);
    }
}
