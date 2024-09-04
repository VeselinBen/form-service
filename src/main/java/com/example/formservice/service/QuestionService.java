package com.example.formservice.service;

import com.example.formservice.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestionById(UUID id);
    List<Question> getAllQuestions();
    Question updateQuestion(UUID id, Question question);
    void deleteQuestion(UUID id);
}
