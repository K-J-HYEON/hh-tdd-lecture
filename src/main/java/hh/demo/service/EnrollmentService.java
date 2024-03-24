package hh.demo.service;

import hh.demo.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // 등록되었는지 확인
    @Transactional(readOnly = true)
    public boolean existEnrollByLectureIdAndUserId(String lectureId, String userId) {
        return enrollmentRepository.findByIdLectureIdUserId(lectureId, userId).isPresent();
    }
}
