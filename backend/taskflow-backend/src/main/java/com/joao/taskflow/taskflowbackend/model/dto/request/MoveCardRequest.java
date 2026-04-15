package com.joao.taskflow.taskflowbackend.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record MoveCardRequest(
        @NotNull Long newListId,
        @NotNull Integer newPosition
) {}