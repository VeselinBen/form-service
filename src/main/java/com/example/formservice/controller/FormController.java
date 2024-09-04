package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.EmailInvitation;
import com.example.formservice.model.Form;
import com.example.formservice.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forms/forms")
@AllArgsConstructor
public class FormController {

    private final FormService formService;
    private final SuccessResponseHandler successResponseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<Form>> createForm(@RequestBody Form form) {
        var createdForm = formService.createForm(form);
        return successResponseHandler.created(createdForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Form>> getFormById(@PathVariable UUID id) {
        Form form = formService.getFormById(id);
        return successResponseHandler.ok(form);
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<Form>>> getAllForms() {
        List<Form> forms = formService.getAllForms();
        return successResponseHandler.ok(forms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Form>> updateForm(@PathVariable UUID id, @RequestBody Form form) {
        Form updatedForm = formService.updateForm(id, form);
        return successResponseHandler.ok(updatedForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Void>> deleteForm(@PathVariable UUID id) {
        formService.deleteForm(id);
        return successResponseHandler.noContent();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<SuccessfulResponse<List<Form>>> getFormsBy(@PathVariable UUID id) {
        List<Form> forms = formService.getFormsByUserId(id);
        return successResponseHandler.ok(forms);
    }
}
