package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.MedicalRecord;
import com.example.uninter.projeto_backend_api.entity.Prescription;
import com.example.uninter.projeto_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

    List<Prescription> findByPatient(User patient);
    List<Prescription> findByProfessional(User professional);
}
