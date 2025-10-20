package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
