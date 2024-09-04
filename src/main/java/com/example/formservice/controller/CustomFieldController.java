package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.CustomField;
import com.example.formservice.service.CustomFieldService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/forms/customfields")
public class CustomFieldController {

    private final CustomFieldService customFieldService;
    private final SuccessResponseHandler successResponseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<CustomField>> createCustomField(@RequestBody CustomField customField) {
        CustomField createdField = customFieldService.createCustomField(customField);
        return successResponseHandler.created(createdField);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<CustomField>> getCustomFieldById(@PathVariable UUID id) {
        CustomField customField = customFieldService.getCustomFieldById(id);
        return successResponseHandler.ok(customField);
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<CustomField>>> getAllCustomFields() {
        List<CustomField> customFields = customFieldService.getAllCustomFields();
        return successResponseHandler.ok(customFields);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<CustomField>> updateCustomField(@PathVariable UUID id, @RequestBody CustomField customField) {
        CustomField updatedField = customFieldService.updateCustomField(id, customField);
        return successResponseHandler.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Void>> deleteCustomField(@PathVariable UUID id) {
        customFieldService.deleteCustomField(id);
        return successResponseHandler.noContent();
    }
}
