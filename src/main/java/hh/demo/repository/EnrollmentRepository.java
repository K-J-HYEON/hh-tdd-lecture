package hh.demo.repository;

import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentId;
import hh.demo.domain.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    Optional<Enrollment> findByIdLectureIdAndUserId(String lectureId, String userId);

    List<Enrollment> findbyIdLectureIdAndUserIdAndStatus(String lectureId, String userId, EnrollmentStatus status);
}
