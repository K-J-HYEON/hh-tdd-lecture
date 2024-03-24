package hh.cleancode.application.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @OneToMany(mappedBy = "application")
    private List<ApplicationRegistrationInfo> ApplicationRegistrationInfo = new ArrayList<>();

}
