package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.Form;
import com.example.formservice.repository.FormRepository;
import com.example.formservice.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;

    @Override
    public Form createForm(Form form) {
        try {
            return formRepository.save(form);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create form. Possible data integrity violation.");
        }
    }

    @Override
    public Form getFormById(UUID id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form updateForm(UUID id, Form form) {
        if (!formRepository.existsById(id)) {
            throw new ResourceNotFoundException("Form not found with id: " + id);
        }
        form.setId(id);
        try {
            return formRepository.save(form);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update form. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteForm(UUID id) {
        if (!formRepository.existsById(id)) {
            throw new ResourceNotFoundException("Form not found with id: " + id);
        }
        try {
            formRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete form. Possible data integrity violation.");
        }
    }

    @Override
    public List<Form> getFormsByUserId(UUID userId) {
        return formRepository.findFormsByUserId(userId);
    }
}
