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
    private long registrationId;

    private LocalDateTime applicationDate;

    private boolean applicationStatus;

    @OneToMany(mappedBy = "registration")
    private List<ApplicationRegistrationInfo> applicationRegistrationInfos = new ArrayList<>();
}