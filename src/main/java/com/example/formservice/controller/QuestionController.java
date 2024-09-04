package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.Form;
import com.example.formservice.model.Question;
import com.example.formservice.service.QuestionService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/forms/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final SuccessResponseHandler successResponseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<Question>> createQuestion(@RequestBody Question question) {
        return successResponseHandler.created(questionService.createQuestion(question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Question>> getQuestionById(@PathVariable UUID id) {
        return successResponseHandler.ok(questionService.getQuestionById(id));
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<Question>>> getAllQuestions() {
        return successResponseHandler.ok(questionService.getAllQuestions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Question>> updateQuestion(@PathVariable UUID id, @RequestBody Question question) {
        return successResponseHandler.ok(questionService.updateQuestion(id, question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Question>> deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return successResponseHandler.noContent();
    }
}
