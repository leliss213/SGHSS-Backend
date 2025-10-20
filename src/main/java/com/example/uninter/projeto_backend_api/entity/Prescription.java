package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Prescription extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private User professional;

    @Column(nullable = false)
    private LocalDateTime date; // Data da emissão

    private boolean digitalSignature; // Indica se é uma receita digital (telemedicina)

    @Column(nullable = false, length = 2000)
    private String medicationsInfo;
}
