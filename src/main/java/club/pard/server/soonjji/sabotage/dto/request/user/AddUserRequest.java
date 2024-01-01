package club.pard.server.soonjji.sabotage.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddUserRequest {
    @NotBlank(message = "Device ID should not be null or empty")
    private String deviceId;
}
