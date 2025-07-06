package com.back.domain.question.question.controller;

import com.back.domain.question.question.QuestionDto;
import com.back.domain.question.question.entity.Question;
import com.back.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class ApiV1QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    @Transactional(readOnly = true)
    public List<QuestionDto> getItems() {
        List<Question> items = questionService.findAll();

        return items.stream()
                .map(QuestionDto::new)
                .toList();
    }
}
