package club.pard.server.soonjji.sabotage.dto.response.ejection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "당일 탈출 횟수 순위 정보가 담아져 있는 Response")
@Getter
@NoArgsConstructor
public class EjectionRankResponse {
    @Schema(description = "탈출 기록 소유자의 별명", example = "잘난뻐꾸기9999")
    private String nickname;

    @Schema(description = "(당일의) 탈출 횟수")
    private Long ejectionCount;

    public EjectionRankResponse(String nickname, Long ejectionCount){ this.nickname = nickname; this.ejectionCount = ejectionCount; }
}
