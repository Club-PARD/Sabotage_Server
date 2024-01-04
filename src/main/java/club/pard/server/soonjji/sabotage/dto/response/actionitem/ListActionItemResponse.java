package club.pard.server.soonjji.sabotage.dto.response.actionitem;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListActionItemResponse {
    @Schema(description = "Action Item 소유자의 사용자 ID 번호")
    private final Long userId;

    @Schema(description = "Action Item의 목록")
    private final List<ActionItemSimplifiedResponse> actionItems;

    @Schema(description = "Action Item을 더 추가할 수 있는지의 여부")
    private final Boolean canbeAddedMore;

    public static ListActionItemResponse of(Long userId, List<ActionItemSimplifiedResponse> actionItems, Boolean canBeAddedMore){ return new ListActionItemResponse(userId, actionItems, canBeAddedMore); }
}