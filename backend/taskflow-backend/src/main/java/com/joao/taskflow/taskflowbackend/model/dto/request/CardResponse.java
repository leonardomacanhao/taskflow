package com.joao.taskflow.taskflowbackend.model.dto.response;

import com.joao.taskflow.taskflowbackend.model.entity.Priority;
import java.time.LocalDateTime;

public record CardResponse(
        Long id,
        Long taskListId,
        String title,
        String description,
        Priority priority,
        LocalDateTime dueDate,
        Integer position,
        LocalDateTime createdAt
) {}