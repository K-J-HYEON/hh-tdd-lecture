package hh.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class EnrollLectureReq {

    @NotNull
    private String userId;

    @NotNull
    private String lectureId;

    public String getUserId() {
        return null;
    }
}
