package hh.lecture.unit;

import hh.cleancode.application.domain.Application;
import hh.cleancode.application.domain.RegistrationInfo;
import hh.cleancode.application.dto.request.ApplicationRequest;
import hh.cleancode.application.dto.response.ApplicationResponse;
import hh.cleancode.application.repository.ApplicationRepository;
import hh.cleancode.application.service.ApplicationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl sut;

    @Mock
    ApplicationRepository applicationRepository;

    @Test
    @DisplayName("특강 신청을 성공함")
    void charge3() throws InterruptedException {
        long applicationId = 1;
        String name = "첫번째 특강";
        when(applicationRepository.findByApplicationId(applicationId, name)).thenReturn(new Application(applicationId));
        Application application = new Application(applicationId);
        when(applicationRepository.findByApplicationId(anyLong(), anyString())).thenReturn(application);
        Application result = sut.createApplication(applicationId);
        assert result.equals(applicationId);
    }

}