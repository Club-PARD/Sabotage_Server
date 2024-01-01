package club.pard.server.soonjji.sabotage.controller.actionitem;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.actionitem.AddActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.UpdateActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.actionitem.ActionItemSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.actionitem.ActionItemService;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping("/{userId}")
    public ResponseEntity<Response<ActionItemSimplifiedResponse>> add(@PathVariable Long userId, @RequestBody @Valid AddActionItemRequest request)
    {
        return actionItemService.add(userId, request);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<Response<List<ActionItemSimplifiedResponse>>> list(@PathVariable Long userId)
    {
        return actionItemService.list(userId);
    }

    @PatchMapping("/expose/{userId}")
    public ResponseEntity<Response<ActionItemSimplifiedResponse>> expose(@PathVariable Long userId)
    {
        return actionItemService.expose(userId);
    }

    @PatchMapping("/{userId}/{itemId}")
    public ResponseEntity<Response<ActionItemSimplifiedResponse>> update(@PathVariable Long userId, @PathVariable Long itemId, @RequestBody UpdateActionItemRequest request)
    {
        return actionItemService.update(userId, itemId, request);
    }

    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<Response<?>> remove(@PathVariable Long userId, @PathVariable Long itemId)
    {
        return actionItemService.remove(userId, itemId);
    }
}
