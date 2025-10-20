package com.example.uninter.projeto_backend_api.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntryCreateDTO {

    private Long medicalRecordId; // prontuário do paciente
    private Long appointmentId;    // opcional
    private Long prescriptionId;   // opcional
    private LocalDateTime date;
    private String notes;
}
