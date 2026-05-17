package com.event.event_app.service;

import com.event.event_app.dto.request.RegisterRequest;
import com.event.event_app.dto.response.AuthResponse;
import com.event.event_app.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(String email, String password);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
}
