package com.joao.taskflow.taskflowbackend.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record BoardResponse(
        Long id,
        String name,
        String description,
        Long ownerId,
        List<TaskListResponse> taskLists,
        LocalDateTime createdAt
) {}