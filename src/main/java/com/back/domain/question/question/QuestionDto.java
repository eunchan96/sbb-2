package com.back.domain.question.question;

import com.back.domain.question.question.entity.Question;

import java.time.LocalDateTime;

public record QuestionDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String subject,
        String content
) {
    public QuestionDto(Question question) {
        this(
                question.getId(),
                question.getCreateDate(),
                question.getModifyDate(),
                question.getSubject(),
                question.getContent()
        );
    }
}
