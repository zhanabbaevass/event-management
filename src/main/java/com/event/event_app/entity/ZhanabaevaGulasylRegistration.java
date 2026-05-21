package com.event.event_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ZhanabaevaGulasylRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private ZhanabaevaGulasylUser user;

    @ManyToOne
    @JoinColumn(name= "event_id")
    private ZhanabaevaGulasylEvent event;

    private LocalDateTime registeredAt;

    @Enumerated(EnumType.STRING)
    private ZhanabaevaGulasylRegistrationStatus status;
}
