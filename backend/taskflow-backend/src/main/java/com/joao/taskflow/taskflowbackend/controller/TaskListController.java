package com.joao.taskflow.taskflowbackend.controller;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.TaskListResponse;
import com.joao.taskflow.taskflowbackend.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getByBoard(@RequestParam Long boardId) {
        return ResponseEntity.ok(taskListService.findByBoard(boardId));
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> create(@RequestBody @Valid CreateTaskListRequest request, @RequestParam Long boardId) {
        return ResponseEntity.ok(taskListService.create(request, boardId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateTaskListRequest request) {
        return ResponseEntity.ok(taskListService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskListService.delete(id);
        return ResponseEntity.noContent().build();
    }
}