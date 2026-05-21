package com.event.event_app.service.impl;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerRegisterRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerAuthResponse;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerUserResponse;
import com.event.event_app.entity.ZhanabaevaGulasylRole;
import com.event.event_app.entity.ZhanabaevaGulasylUser;
import com.event.event_app.repository.ZhanabaevaGulasylUserRepository;
import com.event.event_app.security.ZhanabaevaGulasylJwtUtil;
import com.event.event_app.service.ZhanabaevaGulasylUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZhanabaevaGulasylUserServiceImpl implements ZhanabaevaGulasylUserService {

    private final ZhanabaevaGulasylUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZhanabaevaGulasylJwtUtil jwtUtil;

    @Override
    public ZhanabaevaGulasylAuthControllerAuthResponse register(ZhanabaevaGulasylAuthControllerRegisterRequest request) {
        ZhanabaevaGulasylUser user = ZhanabaevaGulasylUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ZhanabaevaGulasylRole.USER)
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return ZhanabaevaGulasylAuthControllerAuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public ZhanabaevaGulasylAuthControllerAuthResponse login(String email, String password) {
        ZhanabaevaGulasylUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return ZhanabaevaGulasylAuthControllerAuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public List<ZhanabaevaGulasylAuthControllerUserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ZhanabaevaGulasylAuthControllerUserResponse getUserById(Long id) {
        ZhanabaevaGulasylUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private ZhanabaevaGulasylAuthControllerUserResponse mapToResponse(ZhanabaevaGulasylUser user) {
        return ZhanabaevaGulasylAuthControllerUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}