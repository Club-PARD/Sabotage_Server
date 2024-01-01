package club.pard.server.soonjji.sabotage.controller.user;

import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.user.AddUserRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.user.UserSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Response<UserSimplifiedResponse> register(@RequestBody @Valid final AddUserRequest request)
    {
        return userService.register(request);
    }
}
