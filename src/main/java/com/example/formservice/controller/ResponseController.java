package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.Response;
import com.example.formservice.service.ResponseService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/forms/responses")
public class ResponseController {

    private final ResponseService responseService;
    private final SuccessResponseHandler responseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<Response>> createResponse(@RequestBody Response response) {
        return responseHandler.created(responseService.createResponse(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Response>> getResponseById(@PathVariable UUID id) {
        return responseHandler.ok(responseService.getResponseById(id));
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<Response>>> getAllResponses() {
        return responseHandler.ok(responseService.getAllResponses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Response>> updateResponse(@PathVariable UUID id, @RequestBody Response response) {
        return responseHandler.ok(responseService.updateResponse(id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Void>> deleteResponse(@PathVariable UUID id) {
        responseService.deleteResponse(id);
        return responseHandler.noContent();
    }
}
