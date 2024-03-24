package hh.demo.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "lecture_id")
    private String lectureId;
}
