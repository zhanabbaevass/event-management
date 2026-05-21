package com.event.event_app.service.impl;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerCategoryRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerCategoryResponse;
import com.event.event_app.entity.ZhanabaevaGulasylCategory;
import com.event.event_app.repository.ZhanabaevaGulasylCategoryRepository;
import com.event.event_app.service.ZhanabaevaGulasylCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZhanabaevaGulasylCategoryServiceImpl implements ZhanabaevaGulasylCategoryService {

    private final ZhanabaevaGulasylCategoryRepository categoryRepository;

    @Override
    public ZhanabaevaGulasylAuthControllerCategoryResponse create(ZhanabaevaGulasylAuthControllerCategoryRequest request) {
        ZhanabaevaGulasylCategory category = ZhanabaevaGulasylCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return mapToResponse(categoryRepository.save(category));
    }

    @Override
    public List<ZhanabaevaGulasylAuthControllerCategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ZhanabaevaGulasylAuthControllerCategoryResponse getById(Long id) {
        ZhanabaevaGulasylCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapToResponse(category);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerCategoryResponse update(Long id, ZhanabaevaGulasylAuthControllerCategoryRequest request) {
        ZhanabaevaGulasylCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return mapToResponse(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private ZhanabaevaGulasylAuthControllerCategoryResponse mapToResponse(ZhanabaevaGulasylCategory category) {
        return ZhanabaevaGulasylAuthControllerCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
