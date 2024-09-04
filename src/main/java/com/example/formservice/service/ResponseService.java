package com.example.formservice.service;

import com.example.formservice.model.Response;

import java.util.UUID;
import java.util.List;

public interface ResponseService {
    Response createResponse(Response response);
    Response getResponseById(UUID id);
    List<Response> getAllResponses();
    Response updateResponse(UUID id, Response response);
    default void deleteResponse(UUID id) {
    }
}
