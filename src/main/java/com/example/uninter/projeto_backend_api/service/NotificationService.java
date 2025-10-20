package com.example.uninter.projeto_backend_api.service;

import com.example.uninter.projeto_backend_api.entity.DTO.NotificationDTO;
import com.example.uninter.projeto_backend_api.entity.Notification;
import com.example.uninter.projeto_backend_api.entity.User;
import com.example.uninter.projeto_backend_api.repository.NotificationRepository;
import com.example.uninter.projeto_backend_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void notifyUser(Long userId, String title, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Notification notification = Notification.builder()
                .user(user)
                .title(title)
                .message(message)
                .readFlag(false)
                .build();

        notificationRepository.save(notification);
    }

    public List<NotificationDTO> getUserNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return notificationRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // função para atualizar a notificação como lida
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        notification.setReadFlag(true);
        notificationRepository.save(notification);
    }

    private NotificationDTO mapToDTO(Notification n) {
        return NotificationDTO.builder()
                .id(n.getId())
                .userId(n.getUser().getId())
                .title(n.getTitle())
                .message(n.getMessage())
                .readFlag(n.isReadFlag())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
