package com.example.uninter.projeto_backend_api.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionCreateDTO {

    private Long appointmentId; // ligação com o Appointment
    private LocalDateTime date;
    private boolean digitalSignature;
    private String medicationsInfo;
}
