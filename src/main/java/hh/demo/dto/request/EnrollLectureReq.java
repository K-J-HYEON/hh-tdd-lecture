package hh.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class EnrollLectureReq {

    @NotNull
    private long userId;

    @NotNull
    private long lectureId;
}
