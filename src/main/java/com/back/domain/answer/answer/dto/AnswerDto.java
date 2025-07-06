package com.back.domain.answer.answer.dto;

import com.back.domain.answer.answer.entity.Answer;

import java.time.LocalDateTime;

public record AnswerDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String content
) {
    public AnswerDto(Answer answer){
        this(
                answer.getId(),
                answer.getCreateDate(),
                answer.getModifyDate(),
                answer.getContent()
        );
    }
}
