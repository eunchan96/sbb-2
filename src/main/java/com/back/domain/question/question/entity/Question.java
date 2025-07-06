package com.back.domain.question.question.entity;

import com.back.domain.answer.answer.entity.Answer;
import com.back.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseEntity {
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public Question(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public Answer addAnswer(String content) {
        Answer answer = new Answer(content, this);

        answers.add(answer);
        return answer;
    }

    public void modify(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public Optional<Answer> findAnswerById(int id) {
        return answers.stream()
                .filter(answer -> answer.getId() == id)
                .findFirst();
    }

    public boolean deleteAnswer(Answer answer) {
        if (answer == null) return false;
        return answers.remove(answer);
    }
}