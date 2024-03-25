package hh.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LectureDto {

    private long enrollmentMax;

    private long enrollmentNumber;

    public LectureDto(long enrollmentMax, long enrollmentNumber) {
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
