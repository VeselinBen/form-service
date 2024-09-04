package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.Answer;
import com.example.formservice.service.AnswerService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/forms/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final SuccessResponseHandler responseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<Answer>> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.createAnswer(answer);
        return responseHandler.created(createdAnswer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Answer>> getAnswerById(@PathVariable UUID id) {
        Answer answer = answerService.getAnswerById(id);
        return responseHandler.ok(answer);
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<Answer>>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return responseHandler.ok(answers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Answer>> updateAnswer(@PathVariable UUID id, @RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(id, answer);
        return responseHandler.ok(updatedAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Void>> deleteAnswer(@PathVariable UUID id) {
        answerService.deleteAnswer(id);
        return responseHandler.noContent();
    }
}
