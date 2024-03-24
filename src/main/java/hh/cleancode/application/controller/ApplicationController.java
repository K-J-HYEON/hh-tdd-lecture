package hh.cleancode.application.controller;

import hh.cleancode.application.dto.request.ApplicationRequest;
import hh.cleancode.application.service.ApplicationService;
import hh.cleancode.application.service.ApplicationServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@Slf4j
public class ApplicationController {

    private final ApplicationServiceImpl applicationServiceImpl;
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationServiceImpl, ApplicationService applicationService) {
        this.applicationServiceImpl = applicationServiceImpl;
        this.applicationService = applicationService;
    }

    @PostMapping("/{applicationId}/registration")
    public ResponseEntity<String> createApplication(@Valid @RequestBody ApplicationRequest request) {
        applicationService.createApplication(request);
        return ResponseEntity.ok().body("특강신청이 등록되었습니다.");
    }
}
