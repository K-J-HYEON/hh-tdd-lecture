package hh.demo.service;


import hh.demo.domain.Lecture;
import hh.demo.dto.request.LectureDto;
import hh.demo.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public LectureDto getLectureDtoByLectureId(String lectureId) {
        Lecture lecture = getLectureIdByLecture(lectureId);
        return new LectureDto(lecture.getEnrollmentMax(), lecture.getEnrollmentNumber());
    }

    @Transactional
    public void updateLectureEnrollment(String lecturerId) {
        Lecture lecture = lectureRepository.findWithLockById(lecturerId);
        if (lecture.getEnrollmentNumber() + 1 > lecture.getEnrollmentMax()) {
            throw new RuntimeException("정원 초과");
        }
        lecture.updateEnrollmentNumber(lecture.getEnrollmentNumber() + 1);
        lectureRepository.saveAndFlush(lecture);
    }


    public Lecture getLectureIdByLecture(String lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("강의 정보가 존재하지 않습니다."));
    }
}
