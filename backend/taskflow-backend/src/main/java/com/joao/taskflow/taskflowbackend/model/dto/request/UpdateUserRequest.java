package com.joao.taskflow.taskflowbackend.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(max = 100)
        String name,

        @Email
        String email
) {}