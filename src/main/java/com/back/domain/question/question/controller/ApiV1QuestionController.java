package com.back.domain.question.question.controller;

import com.back.domain.question.question.QuestionDto;
import com.back.domain.question.question.entity.Question;
import com.back.domain.question.question.service.QuestionService;
import com.back.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class ApiV1QuestionController {
    private final QuestionService questionService;

    @GetMapping
    @Transactional(readOnly = true)
    public List<QuestionDto> getItems() {
        List<Question> items = questionService.getList();

        return items.stream()
                .map(QuestionDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public QuestionDto getItem(@PathVariable int id) {
        Question question = questionService.getQuestion(id);
        return new QuestionDto(question);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public RsData<QuestionDto> delete(@PathVariable int id) {
        Question question = questionService.getQuestion(id);
        questionService.delete(question);

        return new RsData<>(
                "200-1",
                "%d번 질문이 삭제되었습니다.".formatted(id),
                new QuestionDto(question)
        );
    }


    record QuestionWriteReqBody(
            @NotBlank
            @Size(min = 2, max = 100)
            String subject,
            @NotBlank
            @Size(min = 2, max = 5000)
            String content
    ) {}

    @PostMapping
    @Transactional
    public RsData<QuestionDto> write(@Valid @RequestBody QuestionWriteReqBody reqBody) {
        Question question = questionService.create(reqBody.subject, reqBody.content);

        return new RsData<>(
                "201-1",
                "%d번 질문이 작성되었습니다.".formatted(question.getId()),
                new QuestionDto(question)
        );
    }


    record QuestionModifyReqBody(
            @NotBlank
            @Size(min = 2, max = 100)
            String subject,
            @NotBlank
            @Size(min = 2, max = 5000)
            String content
    ) {}

    @PutMapping("/{id}")
    @Transactional
    public RsData<QuestionDto> modify(
            @PathVariable int id,
            @Valid @RequestBody QuestionModifyReqBody reqBody
    ) {
        Question question = questionService.getQuestion(id);
        questionService.modify(question, reqBody.subject, reqBody.content);

        return new RsData<>(
                "200-1",
                "%d번 질문이 수정되었습니다.".formatted(question.getId()),
                new QuestionDto(question)
        );
    }
}
