package com.event.event_app.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylAuthControllerVenueResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer capacity;
}