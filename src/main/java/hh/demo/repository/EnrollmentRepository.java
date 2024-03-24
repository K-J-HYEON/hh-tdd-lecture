package hh.demo.repository;

import hh.demo.domain.Enrollment;
import hh.demo.domain.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    Optional<Enrollment> findByIdLectureIdUserId(String lectureId, String userId);
}
