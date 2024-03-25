package hh.demo.service;

import hh.demo.TestException;
import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentStatus;
import hh.demo.dto.request.EnrollLectureReq;
import hh.demo.repository.EnrollmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * 특강 신청 테스트
 * 1. 특강 신청 성공
 * 2. 다른 신청자가 이미 특강을 신청한 경우
 * 3. 특강 신청이 30명 초과됐을 경우
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
    void EnrollmentTest() throws Exception {
        // given
        String userId = "test1";
        String lectureId = "test2";
        EnrollLectureReq req = new EnrollLectureReq(userId, lectureId);
        EnrollmentStatus status = EnrollmentStatus.ENROLL_SUCCESS;

        // when
        Optional<Enrollment> enrollment = sut.saveEnrollmentStatus(userId, lectureId, status);

        // then
        assertThat(enrollment.equals(lectureId)).isEqualTo(lectureId);
    }



    // 다른 신청자가 이미 특강을 신청한 경우
    @Test
    @DisplayName("다른 신청자가 이미 특강을 신청한 경우")
    void EnrollmentTest2() throws Exception {
        // Given
        String userId = "test1";
        String lectureId = "test2";
        EnrollLectureReq req = new EnrollLectureReq(userId, lectureId);
        sut.existEnrollByLectureIdAndUserId(userId, lectureId);

        // when ** then
        assertThrows(RuntimeException.class, () -> {
            enrollmentRepository.findByIdLectureIdAndUserId(userId, lectureId);
        });
    }

    @Test
    @DisplayName("특강 신청이 30명 초과됐을 경우")
    void EnrollmentTest3() throws Exception {
        String userId = "test1";
        String lectureId = "test2";
        EnrollLectureReq req = new EnrollLectureReq(userId, lectureId);

        for (long i = 0; i < 30; i++) {
            enrollmentRepository.findByIdLectureIdAndUserId(lectureId, userId);
        }

        assertThrows(RuntimeException.class, () -> {
            sut.existEnrollByLectureIdAndUserId(userId, lectureId);
        });
    }
}