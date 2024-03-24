package hh.cleancode.application.domain;

import hh.cleancode.application.domain.Application;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "lecture")
public class Lecture {

    @Id @GeneratedValue
    @Column(name = "lecture_id")
    private Long lectureId;

    @OneToMany
    @JoinColumn(name = "application_id")
    private Application application;


    private LocalDateTime lectureDate;
}
