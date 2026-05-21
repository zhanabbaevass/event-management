package com.event.event_app.repository;

import com.event.event_app.entity.ZhanabaevaGulasylTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ZhanabaevaGulasylTicketRepository extends JpaRepository<ZhanabaevaGulasylTicket, Long> {
    Optional<ZhanabaevaGulasylTicket> findByTicketNumber(String ticketNumber);
}