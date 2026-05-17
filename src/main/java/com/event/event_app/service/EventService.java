package com.event.event_app.service;

import com.event.event_app.dto.request.EventRequest;
import com.event.event_app.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    EventResponse create(EventRequest request, String organizerEmail);
    Page<EventResponse> getAll(String title, String city, Long categoryId, Pageable pageable);
    EventResponse getById(Long id);
    EventResponse update(Long id, EventRequest request);
    void delete(Long id);
    EventResponse uploadPoster(Long id, MultipartFile file);
}
