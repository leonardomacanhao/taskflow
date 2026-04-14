package com.joao.taskflow.taskflowbackend.model.dto.response;

public record AuthResponse(
        String token,
        String email
) {}