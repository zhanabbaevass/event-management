package com.event.event_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZhanabaevaGulasylAuthControllerCategoryRequest {

    @NotBlank
    private String name;

    private String description;
}
