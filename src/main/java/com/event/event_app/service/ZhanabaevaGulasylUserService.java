package com.event.event_app.service;

import com.event.event_app.dto.request.ZhanabaevaGulasylAuthControllerRegisterRequest;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerAuthResponse;
import com.event.event_app.dto.response.ZhanabaevaGulasylAuthControllerUserResponse;
import java.util.List;

public interface ZhanabaevaGulasylUserService {
    ZhanabaevaGulasylAuthControllerAuthResponse register(ZhanabaevaGulasylAuthControllerRegisterRequest request);
    ZhanabaevaGulasylAuthControllerAuthResponse login(String email, String password);
    List<ZhanabaevaGulasylAuthControllerUserResponse> getAllUsers();
    ZhanabaevaGulasylAuthControllerUserResponse getUserById(Long id);
    void deleteUser(Long id);
}
