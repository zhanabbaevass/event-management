package com.event.event_app.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer capacity;
    private String posterImage;
    private String categoryName;
    private String venueName;
    private String venueCity;
    private String organizerEmail;
}
