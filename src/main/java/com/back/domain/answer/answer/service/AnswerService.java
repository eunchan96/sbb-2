package com.back.domain.answer.answer.service;

import com.back.domain.answer.answer.entity.Answer;
import com.back.domain.question.question.entity.Question;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    public Answer create(Question question, String content) {
        return question.addAnswer(content);
    }

    public void modify(Answer answer, String content) {
        answer.modify(content);
    }
}
