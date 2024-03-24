package hh.cleancode.application.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private long id;

    public Application(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @OneToMany(mappedBy = "application")
    @JoinColumn(name = "application_registration_info_id")
    private List<ApplicationRegistrationInfo> applicationRegistrationInfo = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "새로 만들어줄 중간 테이블 이름")
//    private List<RegistrationInfo> registrationInfos = new ArrayList<>();

}
