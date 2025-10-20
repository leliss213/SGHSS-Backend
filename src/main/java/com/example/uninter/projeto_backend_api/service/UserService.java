package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.DTO.UserDTO;
import com.example.uninter.projeto_backend_api.entity.User;
import com.example.uninter.projeto_backend_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .userType(dto.getUserType())
                .cpf(dto.getCpf())
                .build();

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(null) // nunca retornar senha
                .userType(user.getUserType())
                .cpf(user.getCpf())
                .id(user.getId())
                .build();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return mapToDTO(user);
    }
}
