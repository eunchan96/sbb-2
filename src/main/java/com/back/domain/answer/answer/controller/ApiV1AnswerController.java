package com.back.domain.answer.answer.controller;

import com.back.domain.answer.answer.dto.AnswerDto;
import com.back.domain.answer.answer.entity.Answer;
import com.back.domain.answer.answer.service.AnswerService;
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
@RequestMapping("/api/v1/questions/{questionId}/answers")
@RequiredArgsConstructor
public class ApiV1AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    record AnswerWriteReqBody(
            @NotBlank
            @Size(min = 2, max = 500)
            String content
    ) {}

    @PostMapping
    @Transactional
    public RsData<AnswerDto> write(
            @PathVariable int questionId,
            @Valid @RequestBody AnswerWriteReqBody reqBody
    ) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.create(question, reqBody.content);

        questionService.flush();

        return new RsData<>(
                "201-1",
                "%d번 답변이 작성되었습니다.".formatted(answer.getId()),
                new AnswerDto(answer)
        );
    }


    @GetMapping
    @Transactional(readOnly = true)
    public List<AnswerDto> getItems(@PathVariable int questionId) {
        Question question = questionService.getQuestion(questionId);
        List<Answer> items = answerService.getList(question);

        return items.stream()
                .map(AnswerDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public AnswerDto getItem(@PathVariable int questionId, @PathVariable int id) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.getAnswer(question, id);
        return new AnswerDto(answer);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public RsData<AnswerDto> delete(@PathVariable int questionId, @PathVariable int id) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.getAnswer(question, id);

        answerService.delete(question, answer);

        return new RsData<>(
                "200-1",
                "%d번 답변이 삭제되었습니다.".formatted(id),
                new AnswerDto(answer)
        );
    }


    record AnswerModifyReqBody(
            @NotBlank
            @Size(min = 2, max = 500)
            String content
    ) {}

    @PutMapping("/{id}")
    @Transactional
    public RsData<AnswerDto> modify(
            @PathVariable int questionId, @PathVariable int id,
            @Valid @RequestBody AnswerModifyReqBody reqBody
    ) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = answerService.getAnswer(question, id);

        answerService.modify(answer, reqBody.content);

        return new RsData<>(
                "200-1",
                "%d번 답변이 수정되었습니다.".formatted(id),
                new AnswerDto(answer)
        );
    }
}
