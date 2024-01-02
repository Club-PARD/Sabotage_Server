package club.pard.server.soonjji.sabotage.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "사용자 추가 시 보내는 요청 내용")
@Getter
@RequiredArgsConstructor
public class AddUserRequest {
    @Schema(description = "사용자의 기기 ID. null이거나 비어있을 수 없음")
    @NotBlank(message = "Device ID should not be null or empty")
    private String deviceId;
}
