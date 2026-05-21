package com.event.event_app.service;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerVenueRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerVenueResponse;
import java.util.List;

public interface ZhanabaevaGulasylVenueService {
    ZhanabaevaGulasylAuthControllerVenueResponse create(ZhanabaevaGulasylAuthControllerVenueRequest request);
    List<ZhanabaevaGulasylAuthControllerVenueResponse> getAll();
    ZhanabaevaGulasylAuthControllerVenueResponse getById(Long id);
    ZhanabaevaGulasylAuthControllerVenueResponse update(Long id, ZhanabaevaGulasylAuthControllerVenueRequest request);
    void delete(Long id);
}
