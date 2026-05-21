package com.event.event_app.service.impl;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerVenueRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerVenueResponse;
import com.event.event_app.entity.ZhanabaevaGulasylVenue;
import com.event.event_app.repository.ZhanabaevaGulasylVenueRepository;
import com.event.event_app.service.ZhanabaevaGulasylVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZhanabaevaGulasylVenueServiceImpl implements ZhanabaevaGulasylVenueService {

    private final ZhanabaevaGulasylVenueRepository venueRepository;

    @Override
    public ZhanabaevaGulasylAuthControllerVenueResponse create(ZhanabaevaGulasylAuthControllerVenueRequest request) {
        ZhanabaevaGulasylVenue venue = ZhanabaevaGulasylVenue.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .capacity(request.getCapacity())
                .build();
        return mapToResponse(venueRepository.save(venue));
    }

    @Override
    public List<ZhanabaevaGulasylAuthControllerVenueResponse> getAll() {
        return venueRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ZhanabaevaGulasylAuthControllerVenueResponse getById(Long id) {
        ZhanabaevaGulasylVenue venue = venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        return mapToResponse(venue);
    }

    @Override
    public ZhanabaevaGulasylAuthControllerVenueResponse update(Long id, ZhanabaevaGulasylAuthControllerVenueRequest request) {
        ZhanabaevaGulasylVenue venue = venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        venue.setName(request.getName());
        venue.setAddress(request.getAddress());
        venue.setCity(request.getCity());
        venue.setCapacity(request.getCapacity());
        return mapToResponse(venueRepository.save(venue));
    }

    @Override
    public void delete(Long id) {
        venueRepository.deleteById(id);
    }

    private ZhanabaevaGulasylAuthControllerVenueResponse mapToResponse(ZhanabaevaGulasylVenue venue) {
        return ZhanabaevaGulasylAuthControllerVenueResponse.builder()
                .id(venue.getId())
                .name(venue.getName())
                .address(venue.getAddress())
                .city(venue.getCity())
                .capacity(venue.getCapacity())
                .build();
    }
}
