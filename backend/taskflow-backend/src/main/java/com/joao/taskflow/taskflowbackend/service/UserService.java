package com.joao.taskflow.taskflowbackend.service;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateUserRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateUserRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.UserResponse;
import com.joao.taskflow.taskflowbackend.model.entity.User;
import com.joao.taskflow.taskflowbackend.model.mapper.UserMapper;
import com.joao.taskflow.taskflowbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> findAll() {
        return UserMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return UserMapper.toResponse(user);
    }

    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = UserMapper.toEntity(request, passwordEncoder);
        return UserMapper.toResponse(userRepository.save(user));
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        UserMapper.updateEntity(user, request);
        return UserMapper.toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}