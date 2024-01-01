package club.pard.server.soonjji.sabotage.controller.goalgroup;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.goalgroup.AddGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.UpdateGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.goalgroup.GoalGroupSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.goalgroup.GoalGroupService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goalGroup")
@RequiredArgsConstructor
public class GoalGroupController {
    private final GoalGroupService goalGroupService;

    @PostMapping("/{userId}")
    public Response<GoalGroupSimplifiedResponse> add(@PathVariable Long userId, @RequestBody AddGoalGroupRequest request)
    {
        return goalGroupService.add(userId, request);
    }

    @GetMapping("/{userId}")
    public Response<List<GoalGroupSimplifiedResponse>> list(@PathVariable Long userId)
    {
        return goalGroupService.list(userId);
    }

    @PatchMapping("/{userId}/{goalGroupId}")
    public Response<GoalGroupSimplifiedResponse> update(@PathVariable Long userId, @PathVariable Long goalGroupId, @RequestBody UpdateGoalGroupRequest request)
    {
        return goalGroupService.update(userId, goalGroupId, request);
    }

    @DeleteMapping("/{userId}/{goalGroupId}")
    public Response<?> remove(@PathVariable Long userId, @PathVariable Long goalGroupId)
    {
        return goalGroupService.remove(userId, goalGroupId);
    }
}
