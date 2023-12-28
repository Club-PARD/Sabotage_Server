package club.pard.server.soonjji.sabotage.controller.user;

import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.entity.user.User;
import club.pard.server.soonjji.sabotage.service.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Response<User> register(String deviceId)
    {
        return userService.register(deviceId);
    }
}
