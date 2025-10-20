package com.example.uninter.projeto_backend_api.controller;

import com.example.uninter.projeto_backend_api.entity.DTO.PrescriptionCreateDTO;
import com.example.uninter.projeto_backend_api.entity.Prescription;
import com.example.uninter.projeto_backend_api.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professional/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody PrescriptionCreateDTO dto) {
        return ResponseEntity.ok(prescriptionService.createPrescription(dto));
    }
}
