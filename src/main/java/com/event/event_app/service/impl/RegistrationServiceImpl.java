package com.event.event_app.service.impl;

import com.event.event_app.dto.response.RegistrationResponse;
import com.event.event_app.entity.*;
import com.event.event_app.repository.*;
import com.event.event_app.service.RegistrationService;
import com.event.event_app.service.async.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EmailNotificationService emailNotificationService;

    @Override
    public RegistrationResponse register(Long eventId, String userEmail) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Registration registration = Registration.builder()
                .event(event)
                .user(user)
                .registeredAt(LocalDateTime.now())
                .status(RegistrationStatus.CONFIRMED)
                .build();

        Registration saved = registrationRepository.save(registration);
        log.info("User {} registered for event {}", userEmail, event.getTitle());
        emailNotificationService.sendRegistrationConfirmation(userEmail, event.getTitle());
        return mapToResponse(saved);
    }

    @Override
    public RegistrationResponse cancel(Long registrationId, String userEmail) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        registration.setStatus(RegistrationStatus.CANCELLED);
        Registration saved = registrationRepository.save(registration);
        log.info("User {} cancelled registration {}", userEmail, registrationId);
        emailNotificationService.sendCancellationNotification(userEmail, registration.getEvent().getTitle());
        return mapToResponse(saved);
    }

    @Override
    public List<RegistrationResponse> getMyRegistrations(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return registrationRepository.findByUserId(user.getId()).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationResponse> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RegistrationResponse mapToResponse(Registration registration) {
        return RegistrationResponse.builder()
                .id(registration.getId())
                .userEmail(registration.getUser().getEmail())
                .eventTitle(registration.getEvent().getTitle())
                .registeredAt(registration.getRegisteredAt())
                .status(registration.getStatus().name())
                .build();
    }
}