package com.joao.taskflow.taskflowbackend.model.dto.request;

import com.joao.taskflow.taskflowbackend.model.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record CreateCardRequest(
        @NotBlank @Size(max = 200) String title,
        @Size(max = 1000) String description,
        @NotNull Priority priority,
        LocalDateTime dueDate,
        @NotNull Integer position
) {}