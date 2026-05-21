package com.event.event_app.controller;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerEventRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerEventResponse;
import com.event.event_app.service.ZhanabaevaGulasylEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class ZhanabaevaGulasylAuthControllerEventController {

    private final ZhanabaevaGulasylEventService eventService;

    @PostMapping
    public ResponseEntity<ZhanabaevaGulasylAuthControllerEventResponse> create(
            @Valid @RequestBody ZhanabaevaGulasylAuthControllerEventRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(eventService.create(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<ZhanabaevaGulasylAuthControllerEventResponse>> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Long categoryId,
            Pageable pageable) {
        return ResponseEntity.ok(eventService.getAll(title, city, categoryId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerEventResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerEventResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ZhanabaevaGulasylAuthControllerEventRequest request) {
        return ResponseEntity.ok(eventService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/poster")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerEventResponse> uploadPoster(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(eventService.uploadPoster(id, file));
    }
}
