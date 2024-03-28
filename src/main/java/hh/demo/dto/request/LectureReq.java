package hh.demo.dto.request;

import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
public class LectureReq {

    private long enrollmentMax;

    private long enrollmentNumber;

    public LectureReq(long enrollmentMax, long enrollmentNumber) {
        this.enrollmentMax = enrollmentMax;
        this.enrollmentNumber = enrollmentNumber;
    }

    public long getEnrollmentMax() {
        return enrollmentMax;
    }

    public long getEnrollmentNumber() {
        return enrollmentNumber;
    }
}
