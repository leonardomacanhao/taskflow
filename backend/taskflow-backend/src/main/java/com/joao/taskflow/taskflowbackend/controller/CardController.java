package com.joao.taskflow.taskflowbackend.controller;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.MoveCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.ReorderCardsRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateCardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.CardResponse;
import com.joao.taskflow.taskflowbackend.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getByList(@RequestParam Long taskListId) {
        return ResponseEntity.ok(cardService.findByList(taskListId));
    }

    @PostMapping
    public ResponseEntity<CardResponse> create(@RequestBody @Valid CreateCardRequest request, @RequestParam Long taskListId) {
        return ResponseEntity.ok(cardService.create(request, taskListId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateCardRequest request) {
        return ResponseEntity.ok(cardService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/move")
    public ResponseEntity<CardResponse> move(
            @PathVariable Long id,
            @RequestBody @Valid MoveCardRequest request) {
        return ResponseEntity.ok(cardService.move(id, request.newListId(), request.newPosition()));
    }

    @PatchMapping("/lists/{taskListId}/reorder")
    public ResponseEntity<List<CardResponse>> reorder(
            @PathVariable Long taskListId,
            @RequestBody @Valid ReorderCardsRequest request) {
        return ResponseEntity.ok(cardService.reorder(taskListId, request.updates()));
    }
}