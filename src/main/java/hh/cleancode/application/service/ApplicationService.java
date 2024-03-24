package hh.cleancode.application.service;

import hh.cleancode.application.domain.Application;
import hh.cleancode.application.dto.request.ApplicationRequest;

public interface ApplicationService {

    // 특강 신청
//    Application findByApplicationId(Long id);
    Application createApplication(ApplicationRequest applicationRequest);

    // 특강 신청 완료 여부 조회
}
