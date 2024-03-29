package hh.lecture.stub;

import hh.demo.domain.enrollment.Enrollment;
import hh.demo.domain.enrollment.EnrollmentId;
import hh.demo.domain.enrollment.EnrollmentStatus;
import hh.demo.presentation.dto.request.EnrollLectureReq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StubEnrollmentService extends Enrollment {

    private List<EnrollLectureReq> lectureHistories = new ArrayList<>();

    public StubEnrollmentService(EnrollmentId enrollmentId, EnrollmentStatus status) {
        super(enrollmentId, status);
    }

    public StubEnrollmentService(Enrollment id, EnrollmentStatus enrollmentStatus) {
        super(id, enrollmentStatus);
    }

    public EnrollLectureReq save(String userId, String lectureId) {
        EnrollLectureReq dto = new EnrollLectureReq(
                userId,
                lectureId,
                System.currentTimeMillis()
        );

        lectureHistories.add(dto);

        return dto;
    }

    public void initList(int count) {
        for (int i = 1; i <= count; i++) {
            this.save("test1", "test2");
        }
    }

    public boolean isAlreadyEnrollByUserIdAndLectureId(String userId, String lectureId) {
        return lectureHistories.stream()
                .anyMatch(dto -> Objects.equals(dto.getUserId(), userId)
                        && Objects.equals(dto.getUserId(), lectureId));
    }
}
