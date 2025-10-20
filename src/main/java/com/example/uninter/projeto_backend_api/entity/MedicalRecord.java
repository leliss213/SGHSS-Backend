package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "medical_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord extends BaseEntity{ // Prontuário médico do paciente.

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<RecordEntry> entries; // Entradas de registros médicos
}
