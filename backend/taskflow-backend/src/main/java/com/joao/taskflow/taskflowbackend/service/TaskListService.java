package com.joao.taskflow.taskflowbackend.service;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.TaskListResponse;
import com.joao.taskflow.taskflowbackend.model.mapper.TaskListMapper;
import com.joao.taskflow.taskflowbackend.model.entity.Board;
import com.joao.taskflow.taskflowbackend.model.entity.TaskList;
import com.joao.taskflow.taskflowbackend.repository.BoardRepository;
import com.joao.taskflow.taskflowbackend.repository.TaskListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final BoardRepository boardRepository;

    public TaskListService(TaskListRepository taskListRepository, BoardRepository boardRepository) {
        this.taskListRepository = taskListRepository;
        this.boardRepository = boardRepository;
    }

    public List<TaskListResponse> findByBoard(Long boardId) {
        return TaskListMapper.toResponseList(taskListRepository.findByBoardIdOrderByPositionAsc(boardId));
    }

    public TaskListResponse create(CreateTaskListRequest request, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));
        TaskList taskList = TaskListMapper.toEntity(request, board);
        return TaskListMapper.toResponse(taskListRepository.save(taskList));
    }

    public TaskListResponse update(Long id, UpdateTaskListRequest request) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));
        TaskListMapper.updateEntity(taskList, request);
        return TaskListMapper.toResponse(taskListRepository.save(taskList));
    }

    public void delete(Long id) {
        if (!taskListRepository.existsById(id)) {
            throw new EntityNotFoundException("List not found");
        }
        taskListRepository.deleteById(id);
    }
}