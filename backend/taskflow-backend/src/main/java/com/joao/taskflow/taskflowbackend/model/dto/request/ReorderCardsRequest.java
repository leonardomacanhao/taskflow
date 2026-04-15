package com.joao.taskflow.taskflowbackend.model.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ReorderCardsRequest(
        @NotNull List<CardPositionUpdate> updates
) {
    public record CardPositionUpdate(
            @NotNull Long cardId,
            @NotNull Integer newPosition
    ) {}
}