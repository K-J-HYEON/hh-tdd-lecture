package hh.cleancode.application.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "application_registration_info")
public class ApplicationRegistrationInfo {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private RegistrationInfo registrationInfo;

}
