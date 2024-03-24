package hh.cleancode.application.service;

import hh.cleancode.application.domain.Application;
import hh.cleancode.application.dto.request.ApplicationRequest;
import hh.cleancode.application.repository.ApplicationRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationService applicationService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationService applicationService) {
        this.applicationRepository = applicationRepository;
        this.applicationService = applicationService;
    }

    @Override
    public Application createApplication(ApplicationRequest applicationRequest) {
        Application application = applicationService.createApplication(applicationRequest);
        return application;
    }
}
