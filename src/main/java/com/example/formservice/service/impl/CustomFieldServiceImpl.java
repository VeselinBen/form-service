package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.CustomField;
import com.example.formservice.repository.CustomFieldRepository;
import com.example.formservice.service.CustomFieldService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomFieldServiceImpl implements CustomFieldService {

    private final CustomFieldRepository customFieldRepository;

    @Override
    public CustomField createCustomField(CustomField customField) {
        try {
            return customFieldRepository.save(customField);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create custom field. Possible data integrity violation.");
        }
    }

    @Override
    public CustomField getCustomFieldById(UUID id) {
        return customFieldRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Custom field not found with id: " + id));
    }

    @Override
    public List<CustomField> getAllCustomFields() {
        return customFieldRepository.findAll();
    }

    @Override
    public CustomField updateCustomField(UUID id, CustomField customField) {
        if (!customFieldRepository.existsById(id)) {
            throw new ResourceNotFoundException("Custom field not found with id: " + id);
        }
        customField.setId(id);
        try {
            return customFieldRepository.save(customField);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update custom field. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteCustomField(UUID id) {
        if (!customFieldRepository.existsById(id)) {
            throw new ResourceNotFoundException("Custom field not found with id: " + id);
        }
        try {
            customFieldRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete custom field. Possible data integrity violation.");
        }
    }
}
