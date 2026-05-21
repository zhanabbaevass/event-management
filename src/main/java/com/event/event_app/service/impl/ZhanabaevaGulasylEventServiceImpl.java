package com.event.event_app.service.impl;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerEventRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerEventResponse;
import com.event.event_app.entity.ZhanabaevaGulasylCategory;
import com.event.event_app.entity.ZhanabaevaGulasylEvent;
import com.event.event_app.entity.ZhanabaevaGulasylUser;
import com.event.event_app.entity.ZhanabaevaGulasylVenue;
import com.event.event_app.repository.ZhanabaevaGulasylCategoryRepository;
import com.event.event_app.repository.ZhanabaevaGulasylEventRepository;
import com.event.event_app.repository.ZhanabaevaGulasylUserRepository;
import com.event.event_app.repository.ZhanabaevaGulasylVenueRepository;
import com.event.event_app.service.ZhanabaevaGulasylEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ZhanabaevaGulasylEventServiceImpl implements ZhanabaevaGulasylEventService {

    private final ZhanabaevaGulasylEventRepository eventRepository;
    private final ZhanabaevaGulasylCategoryRepository categoryRepository;
    private final ZhanabaevaGulasylVenueRepository venueRepository;
    private final ZhanabaevaGulasylUserRepository userRepository;

    @Override
    public ZhanabaevaGulasylAuthControllerEventResponse create(ZhanabaevaGulasylAuthControllerEventRequest request, String organizerEmail) {
        ZhanabaevaGulasylCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        ZhanabaevaGulasylVenue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        ZhanabaevaGulasylUser organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ZhanabaevaGulasylEvent event = ZhanabaevaGulasylEvent.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .capacity(request.getCapacity())
                .category(category)
                .venue(venue)
                .organizer(organizer)
                .build();
        return mapToResponse(eventRepository.save(event));
    }

    @Override
    public Page<ZhanabaevaGulasylAuthControllerEventResponse> getAll(String title, String city, Long categoryId, Pageable pageable) {
        if (title != null) {
            return eventRepository.findByTitleContainingIgnoreCase(title, pageable)
                    .map(this::mapToResponse);
        }
        if (city != null) {
            return eventRepository.findByVenueCity(city, pageable)
                    .map(this::mapToResponse);
        }
        if (categoryId != null) {
            return eventRepository.findByCategoryId(categoryId, pageable)
                    .map(this::mapToResponse);
        }
        return eventRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerEventResponse getById(Long id) {
        ZhanabaevaGulasylEvent event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return mapToResponse(event);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerEventResponse update(Long id, ZhanabaevaGulasylAuthControllerEventRequest request) {
        ZhanabaevaGulasylEvent event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setCapacity(request.getCapacity());
        return mapToResponse(eventRepository.save(event));
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerEventResponse uploadPoster(Long id, MultipartFile file) {
        ZhanabaevaGulasylEvent event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            event.setPosterImage(fileName);
            return mapToResponse(eventRepository.save(event));
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file");
        }
    }

    private ZhanabaevaGulasylAuthControllerEventResponse mapToResponse(ZhanabaevaGulasylEvent event) {
        return ZhanabaevaGulasylAuthControllerEventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .capacity(event.getCapacity())
                .posterImage(event.getPosterImage())
                .categoryName(event.getCategory().getName())
                .venueName(event.getVenue().getName())
                .venueCity(event.getVenue().getCity())
                .organizerEmail(event.getOrganizer().getEmail())
                .build();
    }
}