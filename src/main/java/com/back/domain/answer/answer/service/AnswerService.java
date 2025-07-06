package com.back.domain.answer.answer.service;

import com.back.domain.answer.answer.entity.Answer;
import com.back.domain.question.question.entity.Question;
import com.back.global.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    public Answer create(Question question, String content) {
        return question.addAnswer(content);
    }

    public void modify(Answer answer, String content) {
        answer.modify(content);
    }

    public boolean delete(Question question, Answer answer) {
        return getList(question).remove(answer);
    }

    public Answer getAnswer(Question question, int id) {
        return getList(question).stream()
                .filter(answer -> answer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException("404-1", "해당 답변이 존재하지 않습니다."));
    }

    public List<Answer> getList(Question question) {
        return question.getAnswers();
    }
}
