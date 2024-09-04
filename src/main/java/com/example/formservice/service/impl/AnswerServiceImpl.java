package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.Answer;
import com.example.formservice.repository.AnswerRepository;
import com.example.formservice.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer createAnswer(Answer answer) {
        try {
            return answerRepository.save(answer);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create answer. Possible data integrity violation.");
        }
    }

    @Override
    public Answer getAnswerById(UUID id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with id: " + id));
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer updateAnswer(UUID id, Answer answer) {
        if (!answerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Answer not found with id: " + id);
        }
        answer.setId(id);
        try {
            return answerRepository.save(answer);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update answer. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteAnswer(UUID id) {
        if (!answerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Answer not found with id: " + id);
        }
        try {
            answerRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete answer. Possible data integrity violation.");
        }
    }
}
