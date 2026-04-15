package com.joao.taskflow.taskflowbackend.service;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.ReorderCardsRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.CardResponse;
import com.joao.taskflow.taskflowbackend.model.mapper.CardMapper;
import com.joao.taskflow.taskflowbackend.model.entity.Card;
import com.joao.taskflow.taskflowbackend.model.entity.TaskList;
import com.joao.taskflow.taskflowbackend.repository.CardRepository;
import com.joao.taskflow.taskflowbackend.repository.TaskListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final TaskListRepository taskListRepository;

    public CardService(CardRepository cardRepository, TaskListRepository taskListRepository) {
        this.cardRepository = cardRepository;
        this.taskListRepository = taskListRepository;
    }

    public List<CardResponse> findByList(Long taskListId) {
        return CardMapper.toResponseList(cardRepository.findByTaskListIdOrderByPositionAsc(taskListId));
    }

    public CardResponse create(CreateCardRequest request, Long taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));
        Card card = CardMapper.toEntity(request, taskList);
        return CardMapper.toResponse(cardRepository.save(card));
    }

    public CardResponse update(Long id, UpdateCardRequest request) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        CardMapper.updateEntity(card, request);
        return CardMapper.toResponse(cardRepository.save(card));
    }

    public CardResponse move(Long cardId, Long newListId, Integer newPosition) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        TaskList newList = taskListRepository.findById(newListId)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));

        card.setTaskList(newList);
        card.setPosition(newPosition);
        return CardMapper.toResponse(cardRepository.save(card));
    }

    public void delete(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new EntityNotFoundException("Card not found");
        }
        cardRepository.deleteById(id);
    }

    @Transactional
    public List<CardResponse> reorder(Long taskListId, List<ReorderCardsRequest.CardPositionUpdate> updates) {
        List<Card> cards = cardRepository.findByTaskListIdOrderByPositionAsc(taskListId);
        for (var update : updates) {
            cards.stream()
                    .filter(c -> c.getId().equals(update.cardId()))
                    .findFirst()
                    .ifPresent(c -> c.setPosition(update.newPosition()));
        }
        return CardMapper.toResponseList(cardRepository.saveAll(cards));
    }
}