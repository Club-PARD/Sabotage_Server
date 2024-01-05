package club.pard.server.soonjji.sabotage.dto.response.user;

import club.pard.server.soonjji.sabotage.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Schema(description = "User에서 Device ID를 제외하고 사용자 ID 번호와 별명을 가져오게 하는 Response")
@Getter
@ToString
@RequiredArgsConstructor
public class UserSimplifiedResponse {
    @Schema(description = "사용자 ID 번호")
    private final Long id;

    @Schema(description = "사용자 별명", example = "잘난뻐꾸기9999")
    private final String nickname;

    public static UserSimplifiedResponse from(User user){ return new UserSimplifiedResponse(user.getId(), user.getNickname()); }
}
