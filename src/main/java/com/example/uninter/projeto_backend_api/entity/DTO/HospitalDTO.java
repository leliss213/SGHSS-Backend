package com.example.uninter.projeto_backend_api.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class HospitalDTO {

    private Long id;
    private String name;
    private String cnpj;
    private String phone;
}
