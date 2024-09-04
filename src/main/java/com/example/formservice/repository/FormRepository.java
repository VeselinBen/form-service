package com.example.formservice.repository;

import com.example.formservice.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Query("SELECT f FROM Form f WHERE f.createdBy.id = :userId")
    List<Form> findFormsByUserId(@Param("userId") UUID userId);
}
