package com.event.event_app.controller;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerLoginRequest;
import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerRegisterRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerAuthResponse;
import com.event.event_app.service.ZhanabaevaGulasylUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ZhanabaevaGulasylAuthController {

    private final ZhanabaevaGulasylUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerAuthResponse> register(@Valid @RequestBody ZhanabaevaGulasylAuthControllerRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerAuthResponse> login(@Valid @RequestBody ZhanabaevaGulasylAuthControllerLoginRequest request) {
        return ResponseEntity.ok(userService.login(request.getEmail(), request.getPassword()));
    }
}