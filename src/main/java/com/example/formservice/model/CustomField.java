package com.example.formservice.model;

import com.example.formservice.enums.FieldType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    private String fieldName;
    private String fieldValue;

    @OneToMany(mappedBy = "customField", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Question> questions;
}
