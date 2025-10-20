package com.example.uninter.projeto_backend_api.controller;

import com.example.uninter.projeto_backend_api.entity.DTO.AppointmentDTO;
import com.example.uninter.projeto_backend_api.repository.UserRepository;
import com.example.uninter.projeto_backend_api.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/patient/{email}")
    public ResponseEntity<List<AppointmentDTO>> getByPatient(@PathVariable String email){
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(email));
    }

    @GetMapping("/professional/{email}")
    public ResponseEntity<List<AppointmentDTO>> getByProfessional(@PathVariable String email){
        return ResponseEntity.ok(appointmentService.getAppointmentsByProfessional(email));
    }
}
