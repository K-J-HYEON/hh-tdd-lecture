package hh.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
//@NoArgsConstructor
//@AllArgsConstructor
public class EnrollmentId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "lecture_id")
    private String lectureId;

    public EnrollmentId(String userId, String lectureId) {
        this.userId = userId;
        this.lectureId = lectureId;
    }
}
