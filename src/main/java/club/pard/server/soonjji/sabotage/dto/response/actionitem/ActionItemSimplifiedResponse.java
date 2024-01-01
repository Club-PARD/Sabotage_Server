package club.pard.server.soonjji.sabotage.dto.response.actionitem;

import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActionItemSimplifiedResponse {
    private final Long id;
    private final String category;
    private final String content;
    private final Long exposureCount;

    public static ActionItemSimplifiedResponse from(ActionItem actionItem){ return new ActionItemSimplifiedResponse(actionItem.getId(), actionItem.getCategory(), actionItem.getContent(), actionItem.getExposureCount()); }
}
