package com.example.formservice.service;

import com.example.formservice.model.Form;

import java.util.List;
import java.util.UUID;

public interface FormService {
    Form createForm(Form form);
    Form getFormById(UUID id);
    List<Form> getAllForms();
    Form updateForm(UUID id, Form form);
    void deleteForm(UUID id);
    List<Form> getFormsByUserId(UUID userId);
}
