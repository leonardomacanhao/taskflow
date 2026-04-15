package com.joao.taskflow.taskflowbackend.model.mapper;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.CardResponse;
import com.joao.taskflow.taskflowbackend.model.entity.Card;
import com.joao.taskflow.taskflowbackend.model.entity.TaskList;
import java.util.List;

public class CardMapper {

    public static Card toEntity(CreateCardRequest request, TaskList taskList) {
        Card card = new Card();
        card.setTitle(request.title());
        card.setDescription(request.description());
        card.setPriority(request.priority());
        card.setDueDate(request.dueDate());
        card.setPosition(request.position());
        card.setTaskList(taskList);
        return card;
    }

    public static CardResponse toResponse(Card card) {
        return new CardResponse(
                card.getId(),
                card.getTaskList().getId(),
                card.getTitle(),
                card.getDescription(),
                card.getPriority(),
                card.getDueDate(),
                card.getPosition(),
                card.getCreatedAt()
        );
    }

    public static List<CardResponse> toResponseList(List<Card> cards) {
        return cards.stream().map(CardMapper::toResponse).toList();
    }

    public static void updateEntity(Card card, UpdateCardRequest request) {
        if (request.title() != null) card.setTitle(request.title());
        if (request.description() != null) card.setDescription(request.description());
        if (request.priority() != null) card.setPriority(request.priority());
        if (request.dueDate() != null) card.setDueDate(request.dueDate());
        if (request.position() != null) card.setPosition(request.position());
    }
}