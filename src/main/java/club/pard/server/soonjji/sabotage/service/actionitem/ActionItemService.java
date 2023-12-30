package club.pard.server.soonjji.sabotage.service.actionitem;

import java.util.List;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.AddActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.UpdateActionItemRequest;
import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import club.pard.server.soonjji.sabotage.repository.actionitem.ActionItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActionItemService {
    private final ActionItemRepository actionItemRepository;

    public Response<ActionItem> addActionItem(AddActionItemRequest request)
    {
        String itemCategory = request.getCategory();
        String item = request.getContent();

        if(itemCategory == null || itemCategory.isEmpty())
            return Response.setFailure("Action Item의 카테고리를 반드시 선택해야 합니다");
        if(item == null || item.isEmpty())
            return Response.setFailure("Action Item의 내용이 비어 있을 수 없습니다");
        if(actionItemRepository.existsByCategoryAndContent(itemCategory, item))
            return Response.setFailure("같은 이름의 Action Item이 존재합니다");
        
        try
        {
            ActionItem newActionItem = new ActionItem(itemCategory, item);
            actionItemRepository.save(newActionItem);

            return Response.setSuccess("Successfully added action item", newActionItem);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }

    public Response<List<ActionItem>> listAllActionItems(Long userId)
    {
        try
        {
            List<ActionItem> items = actionItemRepository.findAllByUserId(userId);
            if(items == null) return Response.setFailure("The user does not exist");
            return Response.setSuccess("Successfully retrieved list of action items", items);
        }
        catch(Exception e)
        {
            return Response.setFailure("internal DB Error");
        }
    }

    public Response<List<ActionItem>> exposeNActionItems(Long userId, int n)
    {
        try
        {
            if(n <= 0)
                return Response.setFailure("Length must be positive integer");

            List<ActionItem> items = actionItemRepository.findNLeastExposedActionItems(userId, n);
            if(items == null)
                return Response.setFailure("The user does not exist"); // Expected NOT to be stuck here when the list is empty(not null)
            for(ActionItem item: items)
                item.setExposureCount(item.getExposureCount() + 1);
            return Response.setSuccess("Successfully retrieved list of action items", items);
        }
        catch(Exception e)
        {
            return Response.setFailure("internal DB Error");
        }
    }

    public Response<ActionItem> updateActionItem(Long itemId, UpdateActionItemRequest request)
    {   
        try
        {
            ActionItem actionItem = actionItemRepository.findById(itemId).orElse(null);

            if(actionItem == null) return Response.setFailure("Action item not existent with given ID");

            String itemCategory = request.getCategory();
            String item = request.getContent();

            if(itemCategory == null || itemCategory.isEmpty())
                return Response.setFailure("Category of action item cannot be null or empty");
            if(item == null || item.isEmpty())
                return Response.setFailure("Content of action item cannot be null or empty");
            if(actionItemRepository.existsByCategoryAndContent(itemCategory, item))
                return Response.setFailure("Same action item exists");

            actionItem.setCategory(itemCategory);
            actionItem.setContent(item);

            return Response.setSuccess("Successfully modified action item", actionItem);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }

    public Response<?> removeActionItem(Long itemId)
    {
        try
        {
            if(actionItemRepository.existsById(itemId))
            {
                actionItemRepository.deleteById(itemId);
                return Response.setSuccess("Successfully removed action item", null);
            }
            else
                return Response.setFailure("Non-existent ID");
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }
}
