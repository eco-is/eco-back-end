package com.eco.environet.education.services.impl;

import com.eco.environet.education.dto.EducatorQuestionDto;
import com.eco.environet.education.dto.UserQuestionDto;
import com.eco.environet.education.model.Answer;
import com.eco.environet.education.model.Lecture;
import com.eco.environet.education.model.Question;
import com.eco.environet.education.model.QuestionType;
import com.eco.environet.education.repository.AnswerRepository;
import com.eco.environet.education.repository.QuestionRepository;
import com.eco.environet.education.services.QuestionService;
import com.eco.environet.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public EducatorQuestionDto create(EducatorQuestionDto question) {
        var newQuestion = Question.builder()
                .orderInLecture(question.getOrderInLecture())
                .content(question.getContent())
                .type(QuestionType.valueOf(question.getType()))
                .lecture(new Lecture(question.getLectureId()))
                .build();

        var savedQuestion = questionRepository.save(newQuestion);

        Set<Answer> answers = Mapper.mapSet(question.getAnswers(), Answer.class);
        answers = answers.stream().map(answerRepository::save).collect(Collectors.toSet());
        savedQuestion.setAnswers(answers);

        return Mapper.map(savedQuestion, EducatorQuestionDto.class);
    }

    @Override
    public List<EducatorQuestionDto> findAllByLectureIdForEducator(Long lectureId) {
        return Mapper.mapList(questionRepository.findAllByLecture_Id(lectureId), EducatorQuestionDto.class);
    }

    @Override
    public List<UserQuestionDto> findAllByLectureIdForUser(Long lectureId) {
        return Mapper.mapList(questionRepository.findAllByLecture_Id(lectureId), UserQuestionDto.class);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
