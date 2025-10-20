package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.MedicalRecord;
import com.example.uninter.projeto_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

    Optional<MedicalRecord> findByPatient(User patient);
}
