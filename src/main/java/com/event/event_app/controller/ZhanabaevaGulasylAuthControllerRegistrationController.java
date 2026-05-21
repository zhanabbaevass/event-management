package com.event.event_app.controller;

import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerRegistrationResponse;
import com.event.event_app.service.ZhanabaevaGulasylRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class ZhanabaevaGulasylAuthControllerRegistrationController {

    private final ZhanabaevaGulasylRegistrationService registrationService;

    @PostMapping("/events/{eventId}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerRegistrationResponse> register(
            @PathVariable Long eventId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(registrationService.register(eventId, userDetails.getUsername()));
    }

    @PutMapping("/{registrationId}/cancel")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerRegistrationResponse> cancel(
            @PathVariable Long registrationId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(registrationService.cancel(registrationId, userDetails.getUsername()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ZhanabaevaGulasylAuthControllerRegistrationResponse>> getMyRegistrations(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(registrationService.getMyRegistrations(userDetails.getUsername()));
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<List<ZhanabaevaGulasylAuthControllerRegistrationResponse>> getEventRegistrations(
            @PathVariable Long eventId) {
        return ResponseEntity.ok(registrationService.getEventRegistrations(eventId));
    }
}
