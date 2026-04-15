package com.joao.taskflow.taskflowbackend.model.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateBoardRequest(
        @Size(max = 100) String name,
        @Size(max = 500) String description
) {}