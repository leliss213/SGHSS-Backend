package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private User professional;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private AppointmentType type; // Tipo de atendimento: PRESENTIAL ou TELEMEDICINE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status; // Situação: SCHEDULED, COMPLETED, CANCELED

    @Column(nullable = false)
    private LocalDateTime scheduledDate;

    private String notes; // Obervações
}
