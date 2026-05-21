package com.event.event_app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer capacity;

    private String posterImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ZhanabaevaGulasylCategory category;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private ZhanabaevaGulasylVenue venue;

    @ManyToOne
    @JoinColumn(name="organize_id")
    private ZhanabaevaGulasylUser organizer;


}

