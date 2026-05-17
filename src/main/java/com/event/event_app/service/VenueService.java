package com.event.event_app.service;

import com.event.event_app.dto.request.VenueRequest;
import com.event.event_app.dto.response.VenueResponse;
import java.util.List;

public interface VenueService {
    VenueResponse create(VenueRequest request);
    List<VenueResponse> getAll();
    VenueResponse getById(Long id);
    VenueResponse update(Long id, VenueRequest request);
    void delete(Long id);
}
