package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.Question;
import com.example.formservice.repository.QuestionRepository;
import com.example.formservice.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        try {
            return questionRepository.save(question);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create question. Possible data integrity violation.");
        }
    }

    @Override
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(UUID id, Question question) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        question.setId(id);
        try {
            return questionRepository.save(question);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update question. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteQuestion(UUID id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        try {
            questionRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete question. Possible data integrity violation.");
        }
    }
}
