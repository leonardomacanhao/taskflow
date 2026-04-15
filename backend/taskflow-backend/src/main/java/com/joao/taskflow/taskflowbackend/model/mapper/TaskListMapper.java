package com.joao.taskflow.taskflowbackend.model.mapper;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateTaskListRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.TaskListResponse;
import com.joao.taskflow.taskflowbackend.model.entity.Board;
import com.joao.taskflow.taskflowbackend.model.entity.TaskList;
import java.util.List;

public class TaskListMapper {

    public static TaskList toEntity(CreateTaskListRequest request, Board board) {
        TaskList taskList = new TaskList();
        taskList.setName(request.name());
        taskList.setPosition(request.position());
        taskList.setBoard(board);
        return taskList;
    }

    public static TaskListResponse toResponse(TaskList taskList) {
        return new TaskListResponse(
                taskList.getId(),
                taskList.getBoard().getId(),
                taskList.getName(),
                taskList.getPosition(),
                taskList.getCards().stream().map(CardMapper::toResponse).toList(),
                taskList.getCreatedAt()
        );
    }

    public static List<TaskListResponse> toResponseList(List<TaskList> taskLists) {
        return taskLists.stream().map(TaskListMapper::toResponse).toList();
    }

    public static void updateEntity(TaskList taskList, UpdateTaskListRequest request) {
        if (request.name() != null) taskList.setName(request.name());
        if (request.position() != null) taskList.setPosition(request.position());
    }
}