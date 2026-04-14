package com.joao.taskflow.taskflowbackend.service;

import com.joao.taskflow.taskflowbackend.model.dto.request.CreateUserRequest;
import com.joao.taskflow.taskflowbackend.model.dto.request.LoginRequest;
import com.joao.taskflow.taskflowbackend.model.dto.response.AuthResponse;
import com.joao.taskflow.taskflowbackend.model.entity.User;
import com.joao.taskflow.taskflowbackend.model.mapper.UserMapper;
import com.joao.taskflow.taskflowbackend.repository.UserRepository;
import com.joao.taskflow.taskflowbackend.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(CreateUserRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = UserMapper.toEntity(request, passwordEncoder);
        userRepository.save(user);
        String token = tokenProvider.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        String token = tokenProvider.generateToken(authentication.getName());
        return new AuthResponse(token, request.email());
    }
}