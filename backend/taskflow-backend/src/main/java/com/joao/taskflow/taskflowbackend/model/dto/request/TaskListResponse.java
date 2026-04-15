package com.joao.taskflow.taskflowbackend.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record TaskListResponse(
        Long id,
        Long boardId,
        String name,
        Integer position,
        List<CardResponse> cards,
        LocalDateTime createdAt
) {}