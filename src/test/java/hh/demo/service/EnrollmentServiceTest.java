package hh.demo.service;

import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentStatus;
import hh.demo.dto.request.EnrollLectureReq;
import hh.demo.repository.EnrollmentRepository;
import hh.demo.repository.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/*
 * 특강 신청 테스트
 * 1. 특강 신청 성공
 * 2. 같은 사용자 동일한 특강 중복 신청 불가
 * 3. 특강 신청이 30명 초과됐을 경우
 * */


@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @InjectMocks
    EnrollmentService sut;
    @Mock
    EnrollmentRepository enrollmentRepository;
    LectureRepository lectureRepository;


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

    @Test
    @DisplayName("같은 사용자 동일한 특강 중복 신청 불가")
    void EnrollmentTest2() throws Exception {

        // given
        String userId = "test1";
        String lectureId = "test2";
        EnrollmentStatus status = EnrollmentStatus.ENROLL_FAIL;

        // when
        Optional<Enrollment> enrollment = sut.saveEnrollmentStatus(userId, lectureId, status);

        // then
        assertThat(enrollment.equals(lectureId)).isEqualTo(lectureId);

    }

    @Test
    @DisplayName("특강 신청이 30명 초과됐을 경우")
    void EnrollmentTest3() throws Exception {
        String userId = "test1";
        String lectureId = "test2";
        EnrollmentStatus status = EnrollmentStatus.ENROLL_SUCCESS;

        for (long i = 0; i < 30; i++) {
            enrollmentRepository.findByIdLectureIdAndUserId(lectureId, userId);
        }

        assertThrows(RuntimeException.class, () -> {
            sut.existEnrollByLectureIdAndUserId(userId, lectureId);
        });
    }

     /*
     * 1. 특강 신청 성공한 사용자는 성공했음을 리턴
     * 2,.특강 신청 실패한 사용자는 실패했음을 리턴
     */

    @Test
    @DisplayName("특강 신청 성공한 사용자는 성공했다 리턴")
    void EnrollmentTest4() throws Exception {
        // given
        String userId = "test1";
        String lectureId = "test2";
        EnrollmentStatus status = EnrollmentStatus.ENROLL_SUCCESS;

        // when
        // 수정해야함
        when(enrollmentRepository.findByIdLectureIdAndUserId(userId, lectureId)).thenReturn(Optional.ofNullable(lectureId));
        when(lectureRepository.findWithLockById(lectureId)).thenReturn(true);
        String enrollmentStatus = String.valueOf(sut.existEnrollByLectureIdAndUserId(userId, lectureId));

        // then
        assertThat(enrollmentStatus.equals("신청이 완료되었습니다."));
    }


    @Test
    @DisplayName("특강 신청 실패한 사용자는 실패했다 리턴")
    void EnrollmentTest5() throws Exception {

        // given
        String userId = "test1";
        String lectureId = "test2";
        EnrollmentStatus status = EnrollmentStatus.ENROLL_SUCCESS;

        // when
        // 수정해야함
        when(enrollmentRepository.findByIdLectureIdAndUserId(userId, lectureId)).thenReturn(Optional.ofNullable(lectureId));
        when(lectureRepository.findWithLockById(lectureId)).thenReturn(false);
        String enrollmentStatus = String.valueOf(sut.existEnrollByLectureIdAndUserId(userId, lectureId));

        // then
        assertThat(enrollmentStatus.equals("신청이 완료되었습니다."));


    }
}