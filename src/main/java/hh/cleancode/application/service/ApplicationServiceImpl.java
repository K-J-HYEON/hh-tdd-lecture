package hh.cleancode.application.service;

import hh.cleancode.application.domain.Application;
import hh.cleancode.application.dto.request.ApplicationRequest;
import hh.cleancode.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
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
