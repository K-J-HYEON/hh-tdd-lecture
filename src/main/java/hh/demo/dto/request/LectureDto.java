package hh.demo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureDto {

    private int maxEnrollment;

    private int currentEnrollment;
}
