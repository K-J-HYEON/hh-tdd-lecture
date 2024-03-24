package hh.cleancode.application.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "registration_info")
public class RegistrationInfo {

    @Id @GeneratedValue
    @Column(name = "registration_id")
    private long id;

    private LocalDateTime applicationDate;

    private boolean applicationStatus;

    @OneToMany(mappedBy = "registrationInfo")
    @JoinColumn(name = "application_registration_info_id")
    private List<ApplicationRegistrationInfo> applicationRegistrationInfo = new ArrayList<>();

//    @ManyToMany(mappedBy = "registrationInfos")
//    private List<Application> applications = new ArrayList<>();
}