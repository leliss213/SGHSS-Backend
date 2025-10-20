package com.example.uninter.projeto_backend_api.controller;

import com.example.uninter.projeto_backend_api.entity.DTO.UserDTO;
import com.example.uninter.projeto_backend_api.entity.User;
import com.example.uninter.projeto_backend_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO created = userService.createUser(userDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserDTO> getUserByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(userService.getUserByCpf(cpf));
    }
}
