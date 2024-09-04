package com.example.formservice.service;

import com.example.formservice.model.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    Answer createAnswer(Answer answer);
    Answer getAnswerById(UUID id);
    List<Answer> getAllAnswers();
    Answer updateAnswer(UUID id, Answer answer);
    void deleteAnswer(UUID id);
}
