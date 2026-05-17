package com.event.event_app.repository;

import com.event.event_app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Event> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Event> findByVenueCity(String city, Pageable pageable);
}