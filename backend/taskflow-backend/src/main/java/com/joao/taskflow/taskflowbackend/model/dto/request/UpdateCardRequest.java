package com.joao.taskflow.taskflowbackend.model.dto.request;

import com.joao.taskflow.taskflowbackend.model.entity.Priority;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record UpdateCardRequest(
        @Size(max = 200) String title,
        @Size(max = 1000) String description,
        Priority priority,
        LocalDateTime dueDate,
        Integer position
) {}