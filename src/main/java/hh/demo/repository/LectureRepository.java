package hh.demo.repository;

import hh.demo.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByIdLecutrIdUserId(String lectureId, String userId);
}
