package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.Appointment;
import com.example.uninter.projeto_backend_api.entity.AppointmentStatus;
import com.example.uninter.projeto_backend_api.entity.DTO.AppointmentDTO;
import com.example.uninter.projeto_backend_api.entity.User;
import com.example.uninter.projeto_backend_api.repository.AppointmentRepository;
import com.example.uninter.projeto_backend_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        User patient = userRepository.findByEmail(dto.getPatientEmail())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        User professional = userRepository.findByEmail(dto.getProfessionalEmail())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .professional(professional)
                .type(dto.getType())
                .status(AppointmentStatus.SCHEDULED)
                .scheduledDate(dto.getScheduledDate())
                .notes(dto.getNotes())
                .build();

        Appointment saved = appointmentRepository.save(appointment);
        return mapToDTO(saved);
    }

    public AppointmentDTO cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        appointment.setStatus(AppointmentStatus.CANCELED);
        return mapToDTO(appointmentRepository.save(appointment));
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByPatient(String email) {
        User patient = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return appointmentRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByProfessional(String email) {
        User professional = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return appointmentRepository.findAllByProfessional(professional)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO mapToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .patientEmail(appointment.getPatient().getEmail())
                .professionalEmail(appointment.getProfessional().getEmail())
                .type(appointment.getType())
                .status(appointment.getStatus())
                .scheduledDate(appointment.getScheduledDate())
                .notes(appointment.getNotes())
                .hospitalId(appointment.getHospital() != null ? appointment.getHospital().getId() : null)
                .build();
    }
}
