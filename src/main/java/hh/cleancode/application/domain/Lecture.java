package hh.cleancode.application.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "lecture")
public class Lecture {

    @Id @GeneratedValue
    @Column(name = "lecture_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "lecture")
    @JoinColumn(name = "application_id")
    private List<Application> application = new ArrayList<>();

    private LocalDateTime lectureDate;
}
