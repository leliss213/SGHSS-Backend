package com.example.uninter.projeto_backend_api.entity.DTO;

import com.example.uninter.projeto_backend_api.entity.AppointmentStatus;
import com.example.uninter.projeto_backend_api.entity.AppointmentType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private Long id;

    private String patientEmail;

    private String professionalEmail;

    private AppointmentType type;

    private AppointmentStatus status;

    private LocalDateTime scheduledDate;

    private String notes;

    private Long hospitalId;
}