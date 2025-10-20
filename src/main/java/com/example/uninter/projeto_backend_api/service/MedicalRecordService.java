package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.*;
import com.example.uninter.projeto_backend_api.entity.DTO.RecordEntryCreateDTO;
import com.example.uninter.projeto_backend_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final RecordEntryRepository recordEntryRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;

    // Cria um prontuário para o paciente
    public MedicalRecord createMedicalRecord(Long patientId){
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

        if(medicalRecordRepository.findByPatient(patient).isPresent()){
            throw new RuntimeException("Prontuário já existe para esse paciente.");
        }

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .patient(patient)
                .build();

        return medicalRecordRepository.save(medicalRecord);
    }

    public RecordEntry createRecordEntry(RecordEntryCreateDTO dto){
        MedicalRecord record = medicalRecordRepository.findById(dto.getMedicalRecordId())
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado."));

        Appointment appointment = null;
        if(dto.getAppointmentId() != null){
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment não encontrado."));
        }

        Prescription prescription = null;
        if(dto.getPrescriptionId() != null){
            prescription = prescriptionRepository.findById(dto.getPrescriptionId())
                    .orElseThrow(() -> new RuntimeException("Prescrição não encontrada."));
        }

        RecordEntry entry = RecordEntry.builder()
                .medicalRecord(record)
                .appointment(appointment)
                .prescription(prescription)
                .date(dto.getDate())
                .notes(dto.getNotes())
                .build();

        record.getEntries().add(entry);
        medicalRecordRepository.save(record);

        return entry;
    }

    public List<RecordEntry> getMedicalRecordHistory(Long patientId){
        User pacient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

        MedicalRecord record = medicalRecordRepository.findByPatient(pacient)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado."));

        return record.getEntries().stream()
                .sorted((e1, e2) -> e2.getDate().compareTo(e1.getDate()))
                .toList();
    }
}