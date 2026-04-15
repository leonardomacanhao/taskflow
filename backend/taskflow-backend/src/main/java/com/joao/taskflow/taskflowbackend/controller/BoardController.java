package com.joao.taskflow.taskflowbackend.controller;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.BoardResponse;
import com.joao.taskflow.taskflowbackend.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getByOwner(@RequestParam Long ownerId) {
        return ResponseEntity.ok(boardService.findByOwner(ownerId));
    }

    @PostMapping
    public ResponseEntity<BoardResponse> create(@RequestBody @Valid CreateBoardRequest request, @RequestParam Long ownerId) {
        return ResponseEntity.ok(boardService.create(request, ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateBoardRequest request) {
        return ResponseEntity.ok(boardService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}