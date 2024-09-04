package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.Response;
import com.example.formservice.repository.ResponseRepository;
import com.example.formservice.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Override
    public Response createResponse(Response response) {
        try {
            return responseRepository.save(response);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create response. Possible data integrity violation.");
        }
    }

    @Override
    public Response getResponseById(UUID id) {
        return responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Response not found with id: " + id));
    }

    @Override
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public Response updateResponse(UUID id, Response response) {
        if (!responseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Response not found with id: " + id);
        }
        response.setId(id);
        try {
            return responseRepository.save(response);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update response. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteResponse(UUID id) {
        if (!responseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Response not found with id: " + id);
        }
        try {
            responseRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete response. Possible data integrity violation.");
        }
    }
}
