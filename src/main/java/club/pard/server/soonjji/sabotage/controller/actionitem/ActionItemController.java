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

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.AddActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.UpdateActionItemRequest;
import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import club.pard.server.soonjji.sabotage.service.actionitem.ActionItemService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actionItem")
public class ActionItemController {
    private final ActionItemService actionItemService;

    @PostMapping
    public Response<ActionItem> addActionItem(@RequestBody AddActionItemRequest request)
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
