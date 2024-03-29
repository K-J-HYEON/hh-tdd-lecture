package hh.lecture.demo.service;

import hh.demo.domain.lecture.infra.LectureRepository;
import hh.demo.domain.lecture.infra.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class LectureServiceTest {

    @InjectMocks
    LectureService sut;

    @Mock
    LectureRepository lectureRepository;

    /**
     *  1. 특강 신청 여부조회 테스트
     *  2. 신청한 내역 있으면 애외 처리
     */

    @ParameterizedTest
    @CsvSource(value = {
            "1, true", "0, false"
    })
    @DisplayName("특강 신청 여부 조회")
    void enrollment_true(String userId, String lectureId) {

        // given


        // when


        // then

    }
}