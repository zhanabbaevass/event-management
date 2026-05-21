package com.event.event_app.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylAuthControllerUserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}