package com.event.event_app.service;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerCategoryRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerCategoryResponse;
import java.util.List;

public interface ZhanabaevaGulasylCategoryService {
    ZhanabaevaGulasylAuthControllerCategoryResponse create(ZhanabaevaGulasylAuthControllerCategoryRequest request);
    List<ZhanabaevaGulasylAuthControllerCategoryResponse> getAll();
    ZhanabaevaGulasylAuthControllerCategoryResponse getById(Long id);
    ZhanabaevaGulasylAuthControllerCategoryResponse update(Long id, ZhanabaevaGulasylAuthControllerCategoryRequest request);
    void delete(Long id);
}