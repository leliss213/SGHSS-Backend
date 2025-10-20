package com.example.uninter.projeto_backend_api.controller;

import com.example.uninter.projeto_backend_api.entity.DTO.HospitalDTO;
import com.example.uninter.projeto_backend_api.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO) {
        return ResponseEntity.ok(hospitalService.createHospital(hospitalDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDTO> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }
}
