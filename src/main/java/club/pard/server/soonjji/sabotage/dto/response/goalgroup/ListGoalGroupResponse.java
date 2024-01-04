package club.pard.server.soonjji.sabotage.dto.response.goalgroup;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListGoalGroupResponse {
    @Schema(description = "목표 그룹 소유자의 사용자 ID 번호")
    private final Long userId;

    @Schema(description = "목표 그룹의 목록")
    private final List<GoalGroupSimplifiedResponse> goalGroups;

    @Schema(description = "목표 그룹을 더 추가할 수 있는지의 여부")
    private final Boolean canbeAddedMore;

    public static ListGoalGroupResponse of(Long userId, List<GoalGroupSimplifiedResponse> goalGroups, Boolean canBeAddedMore){ return new ListGoalGroupResponse(userId, goalGroups, canBeAddedMore); }
}