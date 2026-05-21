package com.event.event_app.service.impl;

import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerRegistrationResponse;
import com.event.event_app.entity.*;
import com.event.event_app.repository.*;
import com.event.event_app.service.ZhanabaevaGulasylRegistrationService;
import com.event.event_app.service.async.ZhanabaevaGulasylEmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZhanabaevaGulasylRegistrationServiceImpl implements ZhanabaevaGulasylRegistrationService {

    private final ZhanabaevaGulasylRegistrationRepository registrationRepository;
    private final ZhanabaevaGulasylEventRepository eventRepository;
    private final ZhanabaevaGulasylUserRepository userRepository;
    private final ZhanabaevaGulasylEmailNotificationService emailNotificationService;

    @Override
    public ZhanabaevaGulasylAuthControllerRegistrationResponse register(Long eventId, String userEmail) {
        ZhanabaevaGulasylEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        ZhanabaevaGulasylUser user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ZhanabaevaGulasylRegistration registration = ZhanabaevaGulasylRegistration.builder()
                .event(event)
                .user(user)
                .registeredAt(LocalDateTime.now())
                .status(ZhanabaevaGulasylRegistrationStatus.CONFIRMED)
                .build();

        ZhanabaevaGulasylRegistration saved = registrationRepository.save(registration);
        log.info("User {} registered for event {}", userEmail, event.getTitle());
        emailNotificationService.sendRegistrationConfirmation(userEmail, event.getTitle());
        return mapToResponse(saved);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerRegistrationResponse cancel(Long registrationId, String userEmail) {
        ZhanabaevaGulasylRegistration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        registration.setStatus(ZhanabaevaGulasylRegistrationStatus.CANCELLED);
        ZhanabaevaGulasylRegistration saved = registrationRepository.save(registration);
        log.info("User {} cancelled registration {}", userEmail, registrationId);
        emailNotificationService.sendCancellationNotification(userEmail, registration.getEvent().getTitle());
        return mapToResponse(saved);
    }

    @Override
    public List<ZhanabaevaGulasylAuthControllerRegistrationResponse> getMyRegistrations(String userEmail) {
        ZhanabaevaGulasylUser user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return registrationRepository.findByUserId(user.getId()).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ZhanabaevaGulasylAuthControllerRegistrationResponse> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ZhanabaevaGulasylAuthControllerRegistrationResponse mapToResponse(ZhanabaevaGulasylRegistration registration) {
        return ZhanabaevaGulasylAuthControllerRegistrationResponse.builder()
                .id(registration.getId())
                .userEmail(registration.getUser().getEmail())
                .eventTitle(registration.getEvent().getTitle())
                .registeredAt(registration.getRegisteredAt())
                .status(registration.getStatus().name())
                .build();
    }
}