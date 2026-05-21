package com.event.event_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @OneToOne
    @JoinColumn(name= "registration_id")
    private ZhanabaevaGulasylRegistration registration;

    @Enumerated(EnumType.STRING)
    private ZhanabaevaGulasylTicketStatus status;
}
