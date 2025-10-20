package com.example.uninter.projeto_backend_api.repository;

import com.example.uninter.projeto_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
