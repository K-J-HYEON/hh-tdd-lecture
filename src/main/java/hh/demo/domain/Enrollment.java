package hh.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Enrollment")
@NoArgsConstructor
@AllArgsConstructor
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

    public Enrollment(EnrollmentId enrollmentId, EnrollmentStatus status) {
        this.enrollmentStatus = status;
    }

    public void updatEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
