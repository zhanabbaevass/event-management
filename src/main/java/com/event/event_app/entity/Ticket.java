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
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @OneToOne
    @JoinColumn(name= "registration_id")
    private Registration registration;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
