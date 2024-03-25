package hh.demo;

import hh.demo.domain.EnrollmentStatus;
import lombok.Getter;

@Getter
public class TestException {
    EnrollmentStatus enrollmentStatus;
    public TestException(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
