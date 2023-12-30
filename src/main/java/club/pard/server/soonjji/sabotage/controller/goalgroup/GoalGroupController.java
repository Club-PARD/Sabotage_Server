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

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.AddGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.RemoveGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.UpdateGoalGroupRequest;
import club.pard.server.soonjji.sabotage.entity.goalgroup.GoalGroup;
import club.pard.server.soonjji.sabotage.service.goalgroup.GoalGroupService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goalGroup")
@RequiredArgsConstructor
public class GoalGroupController {
    private final GoalGroupService goalGroupService;

    @PostMapping
    public Response<GoalGroup> addGoalGroup(@RequestBody AddGoalGroupRequest request)
    {
        return goalGroupService.addGoalGroup(request);
    }

    @GetMapping("/{userId}")
    public Response<List<GoalGroup>> getGoalGroups(@PathVariable Long userId)
    {
        return goalGroupService.getGoalGroups(userId);
    }

    @PatchMapping
    public Response<GoalGroup> updateGoalGroup(@RequestBody UpdateGoalGroupRequest request)
    {
        return goalGroupService.updateGoalGroup(request);
    }

    @DeleteMapping
    public Response<?> removeGoalGroup(@RequestBody RemoveGoalGroupRequest request)
    {
        return goalGroupService.removeGoalGroup(request);
    }
}
