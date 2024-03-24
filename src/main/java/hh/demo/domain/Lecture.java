package hh.demo.domain;

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

    @Column(name = "lecture_date")
    @NotNull
    private LocalDateTime lectureDate;

    @Column(name = "enrollment_max")
    @NotNull
    private long enrollmentMax;

    @Column(name = "enrollment_number")
    @NotNull
    private long enrollmentNumber;
}
