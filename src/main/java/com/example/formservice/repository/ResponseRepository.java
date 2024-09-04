package com.example.formservice.repository;

import com.example.formservice.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseRepository extends JpaRepository<Response, UUID> {
}
