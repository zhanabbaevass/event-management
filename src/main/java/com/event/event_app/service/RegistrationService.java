package com.event.event_app.service;

import com.event.event_app.dto.response.RegistrationResponse;
import java.util.List;

public interface RegistrationService {
    RegistrationResponse register(Long eventId, String userEmail);
    RegistrationResponse cancel(Long registrationId, String userEmail);
    List<RegistrationResponse> getMyRegistrations(String userEmail);
    List<RegistrationResponse> getEventRegistrations(Long eventId);
}
