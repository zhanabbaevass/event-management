package com.event.event_app.repository;

import com.event.event_app.entity.ZhanabaevaGulasylRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZhanabaevaGulasylRegistrationRepository extends JpaRepository<ZhanabaevaGulasylRegistration, Long> {
    List<ZhanabaevaGulasylRegistration> findByUserId(Long userId);
    List<ZhanabaevaGulasylRegistration> findByEventId(Long eventId);
}
