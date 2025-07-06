package com.back.global.initData;

import com.back.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    @Autowired
    @Lazy
    private BaseInitData self;

    private final QuestionService questionService;

    @Bean
    ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if(questionService.count() > 0) return;

        String subject1 = "sbb가 무엇인가요?";
        String content1 = "sbb에 대해서 알고 싶습니다.";
        questionService.create(subject1, content1);

        String subject2 = "스프링부트 모델 질문입니다.";
        String content2 = "id는 자동으로 생성되나요?";
        questionService.create(subject2, content2);
    }
}
