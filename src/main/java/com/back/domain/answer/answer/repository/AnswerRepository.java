package com.back.domain.answer.answer.repository;

import com.back.domain.answer.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}