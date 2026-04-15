package com.joao.taskflow.taskflowbackend.model.mapper;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.BoardResponse;
import com.joao.taskflow.taskflowbackend.model.entity.Board;
import com.joao.taskflow.taskflowbackend.model.entity.User;
import java.util.List;

public class BoardMapper {

    public static Board toEntity(CreateBoardRequest request, User owner) {
        Board board = new Board();
        board.setName(request.name());
        board.setDescription(request.description());
        board.setOwner(owner);
        return board;
    }

    public static BoardResponse toResponse(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName(),
                board.getDescription(),
                board.getOwner().getId(),
                board.getTaskLists().stream().map(TaskListMapper::toResponse).toList(),
                board.getCreatedAt()
        );
    }

    public static List<BoardResponse> toResponseList(List<Board> boards) {
        return boards.stream().map(BoardMapper::toResponse).toList();
    }

    public static void updateEntity(Board board, UpdateBoardRequest request) {
        if (request.name() != null) board.setName(request.name());
        if (request.description() != null) board.setDescription(request.description());
    }
}