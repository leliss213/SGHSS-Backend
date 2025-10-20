package com.example.uninter.projeto_backend_api.controller;

import com.example.uninter.projeto_backend_api.entity.DTO.RecordEntryCreateDTO;
import com.example.uninter.projeto_backend_api.entity.MedicalRecord;
import com.example.uninter.projeto_backend_api.entity.RecordEntry;
import com.example.uninter.projeto_backend_api.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // cria o prontuário do paciente
    @PostMapping("/create/{patientId}")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@PathVariable Long patientId){
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(patientId));
    }

    @PostMapping("/entry")
    public ResponseEntity<RecordEntry> createRecordEntry(@RequestBody RecordEntryCreateDTO recordEntryCreateDTO){
        return ResponseEntity.ok(medicalRecordService.createRecordEntry(recordEntryCreateDTO));
    }

    @GetMapping("/history/{patientId}")
    public ResponseEntity<List<RecordEntry>> getMedicalRecordHistory(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordHistory(patientId));
    }
}
