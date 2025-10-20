package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.DTO.HospitalDTO;
import com.example.uninter.projeto_backend_api.entity.Hospital;
import com.example.uninter.projeto_backend_api.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalDTO createHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = mapToEntity(hospitalDTO);
        hospital = hospitalRepository.save(hospital);
        return mapToDTO(hospital);
    }

    public HospitalDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital não encontrado com ID: " + id));
        return mapToDTO(hospital);
    }

    private Hospital mapToEntity(HospitalDTO dto) {
        return Hospital.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cnpj(dto.getCnpj())
                .phone(dto.getPhone())
                .build();
    }

    private HospitalDTO mapToDTO(Hospital entity) {
        return HospitalDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cnpj(entity.getCnpj())
                .phone(entity.getPhone())
                .build();
    }
}
