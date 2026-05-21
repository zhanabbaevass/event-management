package com.event.event_app.controller;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerVenueRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerVenueResponse;
import com.event.event_app.service.ZhanabaevaGulasylVenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class ZhanabaevaGulasylAuthControllerVenueController {

    private final ZhanabaevaGulasylVenueService venueService;

    @PostMapping
    public ResponseEntity<ZhanabaevaGulasylAuthControllerVenueResponse> create(@Valid @RequestBody ZhanabaevaGulasylAuthControllerVenueRequest request) {
        return ResponseEntity.ok(venueService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ZhanabaevaGulasylAuthControllerVenueResponse>> getAll() {
        return ResponseEntity.ok(venueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerVenueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerVenueResponse> update(@PathVariable Long id,
                                                                               @Valid @RequestBody ZhanabaevaGulasylAuthControllerVenueRequest request) {
        return ResponseEntity.ok(venueService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
