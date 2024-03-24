package hh.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @Embedded
    @NotNull
    private Enrollment id;

    @Column(name = "enrollment_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EnrollmentStatus enrollmentStatus;
}
