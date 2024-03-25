package hh.demo.service;

import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentId;
import hh.demo.domain.EnrollmentStatus;
import hh.demo.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // 특강신청 등록 진행
    @Transactional
    public void saveEnrollmentStatus(String lectureId, String userId, EnrollmentStatus status) {
        List<Enrollment> enrollmentList = enrollmentRepository.findbyIdLectureIdAndUserIdAndStatus(userId, lectureId, status);
        if (!CollectionUtils.isEmpty(enrollmentList)) {
            throw new RuntimeException("신청이 진행 중입니다.");
        }
        enrollmentRepository.save(new Enrollment(new EnrollmentId(userId, lectureId), status));
    }

    // 등록되었는지 확인
    @Transactional(readOnly = true)
    public boolean existEnrollByLectureIdAndUserId(String lectureId, String userId) {
        return enrollmentRepository.findByIdLectureIdAndUserId(lectureId, userId).isPresent();
    }

    // 등록 업데이트
    @Transactional
    public void updateEnrollmentStatus(String lectureId, String userId, EnrollmentStatus status) {
        Enrollment enrollment = enrollmentRepository.findByIdLectureIdAndUserId(lectureId, userId).orElseThrow(() -> new RuntimeException("신청 정보가 없습니다.,"));
        enrollment.updateEnrollmentStatus(status);
    }

    // 등록 삭제
    @Transactional
    public void deleteEnrollment(String lectureId, String userId) {
        enrollmentRepository.deleteById(new EnrollmentId(userId, lectureId));
    }
}
