package com.event.event_app.repository;

import com.event.event_app.entity.ZhanabaevaGulasylEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ZhanabaevaGulasylEventRepository extends JpaRepository<ZhanabaevaGulasylEvent, Long> {
    Page<ZhanabaevaGulasylEvent> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<ZhanabaevaGulasylEvent> findByCategoryId(Long categoryId, Pageable pageable);
    Page<ZhanabaevaGulasylEvent> findByVenueCity(String city, Pageable pageable);
}