package club.pard.server.soonjji.sabotage.controller.actionitem;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.actionitem.AddActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.UpdateActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import club.pard.server.soonjji.sabotage.service.actionitem.ActionItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "actionItem", description = "Action Item 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actionItem")
public class ActionItemController {
    private final ActionItemService actionItemService;

    @Operation(summary = "Action Item 추가하기", description = "사용자가 Action Item을 추가했을 때 호출되는 함수")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Action Item이 정상적으로 추가됨을 의미함"),
        @ApiResponse(responseCode = "4xx", description = "Action Item 추가 실패, 실패 원인을 메세지로 표시함"),
        @ApiResponse(responseCode = "500", description = "Action Item 추가 실패, 예외 처리로 발생한 상황이므로 서버 관리자가 추적해야 함")
    })
    @PostMapping
    public Response<ActionItem> addActionItem(@RequestBody @Valid AddActionItemRequest request)
    {
        return actionItemService.addActionItem(request);
    }

    @GetMapping("/{userId}/all")
    public Response<List<ActionItem>> listAllActionItems(@PathVariable Long userId)
    {
        return actionItemService.listAllActionItems(userId);
    }

    @PatchMapping("/expose/{userId}")
    public Response<List<ActionItem>> exposeNActionItems(@PathVariable Long userId, @RequestParam("number") int n)
    {
        return actionItemService.exposeNActionItems(userId, n);
    }

    @PatchMapping("/{userId}")
    public Response<ActionItem> updateActionItem(@PathVariable Long userId, @RequestBody UpdateActionItemRequest request)
    {
        return actionItemService.updateActionItem(userId, request);
    }

    @DeleteMapping("/{itemId}")
    public Response<?> removeActionItem(@PathVariable Long itemId)
    {
        return actionItemService.removeActionItem(itemId);
    }
}
