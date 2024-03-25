package hh.demo.repository;

import hh.demo.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select lect from Lecture lec where l.id = :id")
    Lecture findWithLockById(String id);
}