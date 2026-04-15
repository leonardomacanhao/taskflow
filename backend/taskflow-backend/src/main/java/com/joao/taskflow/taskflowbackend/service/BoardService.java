package com.joao.taskflow.taskflowbackend.service;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateBoardRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.BoardResponse;
import com.joao.taskflow.taskflowbackend.model.entity.Board;
import com.joao.taskflow.taskflowbackend.model.mapper.BoardMapper;
import com.joao.taskflow.taskflowbackend.model.entity.User;
import com.joao.taskflow.taskflowbackend.repository.BoardRepository;
import com.joao.taskflow.taskflowbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<BoardResponse> findByOwner(Long ownerId) {
        return BoardMapper.toResponseList(boardRepository.findByOwnerId(ownerId));
    }

    public BoardResponse create(CreateBoardRequest request, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
        Board board = BoardMapper.toEntity(request, owner);
        return BoardMapper.toResponse(boardRepository.save(board));
    }

    public BoardResponse update(Long id, UpdateBoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));
        BoardMapper.updateEntity(board, request);
        return BoardMapper.toResponse(boardRepository.save(board));
    }

    public void delete(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFoundException("Board not found");
        }
        boardRepository.deleteById(id);
    }
}