package hh.cleancode.application.repository;

import hh.cleancode.application.domain.Application;
import hh.cleancode.application.dto.response.ApplicationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<ApplicationResponse> findByApplicationId(Long applicationId);
}
