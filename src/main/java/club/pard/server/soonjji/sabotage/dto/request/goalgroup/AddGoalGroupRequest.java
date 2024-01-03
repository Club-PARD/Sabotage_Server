package club.pard.server.soonjji.sabotage.dto.request.goalgroup;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "목표 그룹 추가 시 보내는 요청 내용")
@Getter
@NoArgsConstructor
public class AddGoalGroupRequest {
    @Schema(description = "목표 그룹 이름, null이거나 비어있을 수 없음")
    @NotBlank(message = "Title should not be null or empty")
    private String title;

    @Schema(description = "목표에 넣을 앱들 목록. null이거나 비어있을 수 없음")
    @NotNull(message = "Apps list should not be null")
    private List<String> apps;

    @Schema(description = "목표 설정 시간(분 단위). null이거나 0 이하의 정수가 들어갈 수 없음.")
    @NotNull(message = "Time budget should not be null") @Positive(message = "Time budget should be positive")
    private Long timeBudget;

    @Schema(description = "알림 간격 시간(분 단위). null이거나 0 이하의 정수가 들어갈 수 없음.")
    @NotNull(message = "Nudge interval should not be null") @Positive(message = "Nudge interval should be positive")
    private Long nudgeInterval;
}
