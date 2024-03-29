package hh.demo.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
//@AllArgsConstructor
public class EnrollLectureReq {

    @NotNull
    private String userId;

    @NotNull
    private String lectureId;

    private long applyMills;

    public EnrollLectureReq(String userId, String lectureId, long applyMills) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.applyMills = applyMills;
    }


    public String getUserId() {
        return null;
    }
}
