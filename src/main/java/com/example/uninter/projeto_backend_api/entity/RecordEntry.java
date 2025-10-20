package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "appointment_id")
    private Appointment appointment; // consulta relacionada (opcional)

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription; // receita relacionada (opcional)

    private LocalDateTime date;

    @Column(length = 2000, columnDefinition = "TEXT")
    private String notes; // Descrição do registro médico
}
