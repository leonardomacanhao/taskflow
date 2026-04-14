package com.joao.taskflow.taskflowbackend.model.mapper;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateUserRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.UpdateUserRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.UserResponse;
import com.joao.taskflow.taskflowbackend.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

public class UserMapper {

    public static User toEntity(CreateUserRequest request, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email().toLowerCase());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        return user;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        return users.stream().map(UserMapper::toResponse).toList();
    }

    public static void updateEntity(User user, UpdateUserRequest request) {
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.email() != null) {
            user.setEmail(request.email().toLowerCase());
        }
    }
}