package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.MedicalRecord;
import com.example.uninter.projeto_backend_api.entity.RecordEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordEntryRepository extends JpaRepository<RecordEntry,Long> {

    List<RecordEntry> findByMedicalRecord(MedicalRecord medicalRecord);
}
