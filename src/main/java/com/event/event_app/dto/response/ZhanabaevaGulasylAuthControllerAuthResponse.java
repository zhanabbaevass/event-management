package com.event.event_app.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylAuthControllerAuthResponse {

    private String token;
    private String email;
    private String role;
}