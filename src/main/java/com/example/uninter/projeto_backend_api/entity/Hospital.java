package com.example.uninter.projeto_backend_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Hospital extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String name;

    private String cnpj;

    private String phone;
}
