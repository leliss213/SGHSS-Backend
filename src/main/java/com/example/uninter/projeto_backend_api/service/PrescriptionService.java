package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.Appointment;
import com.example.uninter.projeto_backend_api.entity.DTO.PrescriptionCreateDTO;
import com.example.uninter.projeto_backend_api.entity.Prescription;
import com.example.uninter.projeto_backend_api.repository.AppointmentRepository;
import com.example.uninter.projeto_backend_api.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public Prescription createPrescription(PrescriptionCreateDTO dto){
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(()-> new RuntimeException("Agendamento não encontrado"));

        Prescription prescription = Prescription.builder()
                .appointment(appointment)
                .date(dto.getDate())
                .digitalSignature(dto.isDigitalSignature())
                .medicationsInfo(dto.getMedicationsInfo())
                .build();

        return prescriptionRepository.save(prescription);
    }
}
