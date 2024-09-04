package com.example.formservice.service;

import com.example.formservice.model.CustomField;

import java.util.List;
import java.util.UUID;

public interface CustomFieldService {
    CustomField createCustomField(CustomField customField);
    CustomField getCustomFieldById(UUID id);
    List<CustomField> getAllCustomFields();
    CustomField updateCustomField(UUID id, CustomField customField);
    void deleteCustomField(UUID id);
}
