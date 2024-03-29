package hh.demo.domain.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @Column(name = "lecture_id")
    @NotNull
    private String id;

    @Column(name = "lecture_name")
    @NotNull
    private String name;

    @Column(name = "lecture_teacher")
    @NotNull
    private String teacher;

    @Column(name = "lecture_date")
    @NotNull
    private LocalDateTime lectureDate;

    @Column(name = "enrollment_max")
    @NotNull
    private long enrollmentMax;

    @Column(name = "enrollment_number")
    @NotNull
    private long enrollmentNumber;

    public void updateEnrollmentNumber(long enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }

    public long getEnrollmentMax() {
        return enrollmentMax;
    }

    public long getEnrollmentNumber() {
        return enrollmentNumber;
    }
}
