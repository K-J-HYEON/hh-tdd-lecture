package hh.demo.domain.enrollment.infra;

import hh.demo.domain.enrollment.Enrollment;
import hh.demo.domain.enrollment.EnrollmentId;
import hh.demo.domain.enrollment.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    Optional<Enrollment> findByIdLectureIdAndUserId(String lectureId, String userId);

    List<Enrollment> findbyIdLectureIdAndUserIdAndStatus(String lectureId, String userId, EnrollmentStatus status);
}
