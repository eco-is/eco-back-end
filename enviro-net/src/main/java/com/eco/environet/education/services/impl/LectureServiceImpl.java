package com.eco.environet.education.services.impl;

import com.eco.environet.education.dto.LectureCreationRequest;
import com.eco.environet.education.dto.LectureDto;
import com.eco.environet.education.model.Lecture;
import com.eco.environet.education.model.LectureCategory;
import com.eco.environet.education.model.LectureDifficulty;
import com.eco.environet.education.repository.LectureCategoryRepository;
import com.eco.environet.education.repository.LectureRepository;
import com.eco.environet.education.services.LectureService;
import com.eco.environet.users.model.User;
import com.eco.environet.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final LectureCategoryRepository lectureCategoryRepository;
    private final Mapper mapper;
    @Override
    public LectureDto create(LectureCreationRequest lecture) {
        var newLecture = Lecture.builder()
                .name(lecture.getName())
                .content(lecture.getContent())
                .difficulty(LectureDifficulty.valueOf(lecture.getDifficulty().toUpperCase()))
                .minRecommendedAge(lecture.getMinRecommendedAge())
                .maxRecommendedAge(lecture.getMaxRecommendedAge())
                .categories(new HashSet<>())
                .creator(new User(lecture.getCreatorId()))
                .build();

        for (String category : lecture.getCategories()) {
            newLecture.getCategories().add(
                    lectureCategoryRepository.findByDescription(category)
                            .orElseGet(() -> {
                                LectureCategory newCategory = new LectureCategory();
                                newCategory.setDescription(category);
                                return lectureCategoryRepository.save(newCategory);
                            })
            );
        }

        return mapper.map(lectureRepository.save(newLecture), LectureDto.class);
    }

    @Override
    public List<LectureDto> findAllByCreatorId(Long creatorId) {
        return mapper.mapList(lectureRepository.findAllByCreator_Id(creatorId).orElseThrow(), LectureDto.class);
    }

    @Override
    public List<LectureDto> findAll() {
        return mapper.mapList(lectureRepository.findAll(), LectureDto.class);
    }

    @Override
    public void delete(Long id) {
        lectureRepository.deleteById(id);
    }
}
