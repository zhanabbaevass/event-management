package com.event.event_app.service;

import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerRegistrationResponse;
import java.util.List;

public interface ZhanabaevaGulasylRegistrationService {
    ZhanabaevaGulasylAuthControllerRegistrationResponse register(Long eventId, String userEmail);
    ZhanabaevaGulasylAuthControllerRegistrationResponse cancel(Long registrationId, String userEmail);
    List<ZhanabaevaGulasylAuthControllerRegistrationResponse> getMyRegistrations(String userEmail);
    List<ZhanabaevaGulasylAuthControllerRegistrationResponse> getEventRegistrations(Long eventId);
}
