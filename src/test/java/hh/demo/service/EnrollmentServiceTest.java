package hh.demo.service;

import hh.demo.TestException;
import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentStatus;
import hh.demo.repository.EnrollmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;



/*
 * 특강 신청 테스트
 * 1. 특강 신청 성공
 * 2. 특강이 없을 경우
 * 3. 신청자가 이미 특강을 신청한 경우
 * 4. 특강이 다찼을 경우
 * */

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @InjectMocks
    EnrollmentService sut;
    @Mock
    EnrollmentRepository enrollmentRepository;

//    1. 특강 신청 성공
    @Test
    @DisplayName("특강 신청 성공")
    void EnrollmentTest() throws InterruptedException {
        String enrollmentId = "test1";
        String userId = "test2";
        when(enrollmentRepository.findByIdLectureIdAndUserId(enrollmentId, userId)).thenReturn(new Enrollment(enrollmentId, userId));
        Enrollment savedUser = new Enrollment(enrollmentId, userId);
        when(enrollmentRepository.findbyIdLectureIdAndUserIdAndStatus(anyString(), anyString())).thenReturn(savedUser);
        Enrollment result = sut.updateEnrollmentStatus(EnrollmentStatus.ENROLL_SUCCESS);
        assert result.equals(savedUser);
    }

    // 특강이 없으면 예외를 반환
    @Test
    @DisplayName("특강이 없으면 예외를 반환")
    void EnrollmentTest2() throws InterruptedException {
        String enrollmentId = "test1";
        String userId = "test2";
        when(enrollmentRepository.findByIdLectureIdAndUserId(enrollmentId, userId)).thenReturn(null);
        Exception e = null;

        try {
            Enrollment enrollment = sut.saveEnrollmentStatus(enrollmentId, userId, EnrollmentStatus.ENROLLING);
        } catch (Exception exception) {
            e = exception;
        }

        assert e != null;
        assert e instanceof TestException;
        assert ((TestException) e).getMessage().equals(EnrollmentStatus.ENROLL_FAIL);
    }
}