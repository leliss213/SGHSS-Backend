package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.AppointmentStatus;
import com.example.uninter.projeto_backend_api.entity.MedicalRecord;
import com.example.uninter.projeto_backend_api.entity.RecordEntry;
import com.example.uninter.projeto_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordEntryRepository extends JpaRepository<RecordEntry,Long> {

    List<RecordEntry> findByPatient(User patient);
    List<RecordEntry> findByMedicalRecord(MedicalRecord medicalRecord);
}
