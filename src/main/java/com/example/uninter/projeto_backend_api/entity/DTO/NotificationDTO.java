package com.example.uninter.projeto_backend_api.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String title;
    private String message;
    private boolean readFlag;
    private LocalDateTime createdAt;

}
