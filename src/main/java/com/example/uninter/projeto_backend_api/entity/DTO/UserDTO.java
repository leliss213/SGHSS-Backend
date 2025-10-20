package com.example.uninter.projeto_backend_api.entity.DTO;

import com.example.uninter.projeto_backend_api.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String fullName;

    private String email;

    private String password;

    private UserType userType;

    private String cpf;

}
