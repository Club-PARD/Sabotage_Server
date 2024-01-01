package club.pard.server.soonjji.sabotage.service.actionitem;

import java.util.List;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.request.actionitem.AddActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.request.actionitem.UpdateActionItemRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import club.pard.server.soonjji.sabotage.repository.actionitem.ActionItemRepository;
import club.pard.server.soonjji.sabotage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActionItemService {
    private final ActionItemRepository actionItemRepository;
    private final UserRepository userRepository;

    public Response<ActionItem> addActionItem(AddActionItemRequest request)
    {
        String itemCategory = request.getCategory();
        String item = request.getContent();

        if(itemCategory == null || itemCategory.isEmpty())
            return Response.setFailure("Action Item의 카테고리를 반드시 선택해야 해요!", "ActionItem/add: category is null or empty");
        if(item == null || item.isEmpty())
            return Response.setFailure("Action Item의 내용이 비어있어요!", "ActionItem/add: item name is null or empty");
        if(actionItemRepository.existsByCategoryAndContent(itemCategory, item))
            return Response.setFailure("같은 이름의 Action Item이 이미 있어요!", "ActionItem/add: Action item already exists with the same name and category");
        
        try
        {
            ActionItem newActionItem = new ActionItem(itemCategory, item);
            actionItemRepository.save(newActionItem);

            return Response.setSuccess("Action Item을 추가 완료!", "ActionItem/add: Action item successfully added", newActionItem);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버 내부에 오류가 생겼어요!", "ActionItem/add: Internal Server Error");
        }
    }

    public Response<List<ActionItem>> listAllActionItems(Long userId)
    {
        try
        {
            if(!userRepository.existsById(userId))
                return Response.setFailure("사용자가 존재하지 않아요!", "ActionItem/list: Target User does not exist");
            
            List<ActionItem> items = actionItemRepository.findAllByUserId(userId);
            if(items == null) return Response.setFailure("Action Item 리스트가 존재하지 않아요!", "ActionItem/list: Action Item list is null");

            return Response.setSuccess("Action Item 목록 조회 완료!", "ActionItem/list: Successfully retrieved list of action items", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버 내류에 오류가 생겼어요!", "ActionItem/list: Internal Server Error");
        }
    }

    public Response<List<ActionItem>> exposeNActionItems(Long userId, int n)
    {
        try
        {
            if(!userRepository.existsById(userId))
                return Response.setFailure("사용자가 존재하지 않아요!", "ActionItem/expose: Target User does not exist");

            if(n <= 0)
                return Response.setFailure("잘못된 길이가 들어왔어요!", "ActionItem/expose: Target length must be positive integer");

            List<ActionItem> items = actionItemRepository.findNLeastExposedActionItems(userId, n);
            if(items == null)
                return Response.setFailure("Action Item 리스트가 존재하지 않아요!", "ActionItem/expose: Action Item list is null"); // Expected NOT to be stuck here when the list is empty(not null)
            for(ActionItem item: items)
                item.setExposureCount(item.getExposureCount() + 1);
            return Response.setSuccess("", "ActionItem/expose: successful", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버 내류에 오류가 생겼어요!", "ActionItem/expose: Internal Server Error");
        }
    }

    public Response<ActionItem> updateActionItem(Long itemId, UpdateActionItemRequest request)
    {   
        try
        {
            ActionItem actionItem = actionItemRepository.findById(itemId).orElse(null);

            if(actionItem == null) return Response.setFailure("Action item not existent with given ID", "");

            String itemCategory = request.getCategory();
            String item = request.getContent();

            if(itemCategory == null || itemCategory.isEmpty())
                return Response.setFailure("Category of action item cannot be null or empty", "");
            if(item == null || item.isEmpty())
                return Response.setFailure("Content of action item cannot be null or empty", "");
            if(actionItemRepository.existsByCategoryAndContent(itemCategory, item))
                return Response.setFailure("Same action item exists", "");

            actionItem.setCategory(itemCategory);
            actionItem.setContent(item);

            return Response.setSuccess("Successfully modified action item", "", actionItem);
        }
        catch(Exception e)
        {
            return Response.setFailure("서버 내류에 오류가 생겼어요!", "ActionItem/update: Internal Server Error");
        }
    }

    public Response<?> removeActionItem(Long itemId)
    {
        try
        {
            if(actionItemRepository.existsById(itemId))
            {
                actionItemRepository.deleteById(itemId);
                return Response.setSuccess("Successfully removed action item", "", null);
            }
            else
                return Response.setFailure("Non-existent ID", "");
        }
        catch(Exception e)
        {
            return Response.setFailure("서버 내류에 오류가 생겼어요!", "ActionItem/remove: Internal Server Error");
        }
    }
}
