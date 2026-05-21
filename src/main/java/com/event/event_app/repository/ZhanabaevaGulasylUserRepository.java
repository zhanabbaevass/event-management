package com.event.event_app.repository;

import com.event.event_app.entity.ZhanabaevaGulasylUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ZhanabaevaGulasylUserRepository extends JpaRepository<ZhanabaevaGulasylUser, Long> {
    Optional<ZhanabaevaGulasylUser> findByEmail(String email);
}
