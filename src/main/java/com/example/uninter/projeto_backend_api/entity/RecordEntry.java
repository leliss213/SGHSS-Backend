package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordEntry extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord; // Prontuário ao qual pertence

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional; // Profissional que fez o registro

    @Column(length = 2000)
    private String description; // Descrição do registro médico
}
