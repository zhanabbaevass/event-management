package com.event.event_app.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylAuthControllerRegistrationResponse {

    private Long id;
    private String userEmail;
    private String eventTitle;
    private LocalDateTime registeredAt;
    private String status;
}
