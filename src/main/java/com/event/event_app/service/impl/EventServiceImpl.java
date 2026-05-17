package com.event.event_app.service.impl;

import com.event.event_app.dto.request.EventRequest;
import com.event.event_app.dto.response.EventResponse;
import com.event.event_app.entity.Category;
import com.event.event_app.entity.Event;
import com.event.event_app.entity.User;
import com.event.event_app.entity.Venue;
import com.event.event_app.repository.CategoryRepository;
import com.event.event_app.repository.EventRepository;
import com.event.event_app.repository.UserRepository;
import com.event.event_app.repository.VenueRepository;
import com.event.event_app.service.EventService;
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
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;

    @Override
    public EventResponse create(EventRequest request, String organizerEmail) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
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
    public Page<EventResponse> getAll(String title, String city, Long categoryId, Pageable pageable) {
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
    public EventResponse getById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return mapToResponse(event);
    }

    @Override
    public EventResponse update(Long id, EventRequest request) {
        Event event = eventRepository.findById(id)
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
    public EventResponse uploadPoster(Long id, MultipartFile file) {
        Event event = eventRepository.findById(id)
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

    private EventResponse mapToResponse(Event event) {
        return EventResponse.builder()
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