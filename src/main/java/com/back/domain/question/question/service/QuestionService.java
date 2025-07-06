package com.back.domain.question.question.service;

import com.back.domain.question.question.entity.Question;
import com.back.domain.question.question.repositrory.QuestionRepository;
import com.back.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question create(String subject, String content) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        return questionRepository.save(question);
    }

    public long count() {
        return questionRepository.count();
    }

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("404-1", "해당 글이 존재하지 않습니다."));
    }
}
