package com.back.domain.question.question.repositrory;

import com.back.domain.question.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}