package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.Appointment;
import com.example.uninter.projeto_backend_api.entity.AppointmentStatus;
import com.example.uninter.projeto_backend_api.entity.AppointmentType;
import com.example.uninter.projeto_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findAllByProfessional(User professional);
    List<Appointment> findByPatient(User patient);
    List<Appointment> findByStatus(AppointmentStatus status);
    List<Appointment> findByType(AppointmentType type);

}
