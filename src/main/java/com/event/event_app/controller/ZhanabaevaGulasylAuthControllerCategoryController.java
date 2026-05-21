package com.event.event_app.controller;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerCategoryRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerCategoryResponse;
import com.event.event_app.service.ZhanabaevaGulasylCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class ZhanabaevaGulasylAuthControllerCategoryController {

    private final ZhanabaevaGulasylCategoryService categoryService;

    @PostMapping
    public ResponseEntity<ZhanabaevaGulasylAuthControllerCategoryResponse> create(@Valid @RequestBody ZhanabaevaGulasylAuthControllerCategoryRequest request) {
        return ResponseEntity.ok(categoryService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ZhanabaevaGulasylAuthControllerCategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerCategoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZhanabaevaGulasylAuthControllerCategoryResponse> update(@PathVariable Long id,
                                                                                  @Valid @RequestBody ZhanabaevaGulasylAuthControllerCategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
