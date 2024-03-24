package hh.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnrollLectureRes {

    private String lectureId;
    private String userId;
    private Boolean enrollmentResult;

    public EnrollLectureRes(String lectureId, String userId, boolean enrollmentResult) {

    }
}
