package club.pard.server.soonjji.sabotage.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddUserRequest {
    @NotNull
    private String deviceId;
}
