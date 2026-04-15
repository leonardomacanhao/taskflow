package com.joao.taskflow.taskflowbackend.model.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateTaskListRequest(
        @Size(max = 100) String name,
        Integer position
) {}