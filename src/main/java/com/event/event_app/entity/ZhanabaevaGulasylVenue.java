package com.event.event_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ZhanabaevaGulasylVenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    private Integer capacity;
}
