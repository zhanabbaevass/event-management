package com.event.event_app.service;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerEventRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerEventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ZhanabaevaGulasylEventService {
    ZhanabaevaGulasylAuthControllerEventResponse create(ZhanabaevaGulasylAuthControllerEventRequest request, String organizerEmail);
    Page<ZhanabaevaGulasylAuthControllerEventResponse> getAll(String title, String city, Long categoryId, Pageable pageable);
    ZhanabaevaGulasylAuthControllerEventResponse getById(Long id);
    ZhanabaevaGulasylAuthControllerEventResponse update(Long id, ZhanabaevaGulasylAuthControllerEventRequest request);
    void delete(Long id);
    ZhanabaevaGulasylAuthControllerEventResponse uploadPoster(Long id, MultipartFile file);
}
