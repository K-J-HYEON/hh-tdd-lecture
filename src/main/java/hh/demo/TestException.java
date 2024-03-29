package hh.demo;

import hh.demo.domain.enrollment.EnrollmentStatus;
import lombok.Getter;

@Getter
public class TestException {
    EnrollmentStatus enrollmentStatus;
    public TestException(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
