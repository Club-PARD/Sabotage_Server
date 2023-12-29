package club.pard.server.soonjji.sabotage.service.goalgroup;

import java.util.List;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.AddGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.RemoveGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.UpdateGoalGroupRequest;
import club.pard.server.soonjji.sabotage.entity.goalgroup.Goal;
import club.pard.server.soonjji.sabotage.entity.goalgroup.GoalGroup;
import club.pard.server.soonjji.sabotage.entity.user.User;
import club.pard.server.soonjji.sabotage.repository.goalgroup.GoalGroupRepository;
import club.pard.server.soonjji.sabotage.repository.goalgroup.GoalRepository;
import club.pard.server.soonjji.sabotage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalGroupService {
    private final GoalGroupRepository goalGroupRepository;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public Response<GoalGroup> addGoalGroup(AddGoalGroupRequest request)
    {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        String groupTitle = request.getTitle();
        List<String> apps = request.getApps();
        Long timeBudget = request.getTimeBudget();

        if(user == null)
            return Response.setFailure("User not existent");
        if(groupTitle == null || groupTitle.isEmpty())
            return Response.setFailure("Group is empty or null");
        if(apps == null) // can be empty.
            return Response.setFailure("Apps list can be empty but cannot be null");
        
        try
        {
            GoalGroup newGroup = new GoalGroup(user, groupTitle, timeBudget);
            goalGroupRepository.save(newGroup);

            for(String app: apps)
            {
                Goal newGoal = new Goal(user, newGroup, app);
                newGroup.addGoal(newGoal);

                goalRepository.save(newGoal);
            }

            return Response.setSuccess("Successfully added goal group and its goals", newGroup);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }

    public Response<List<GoalGroup>> getGoalGroups(Long userId)
    {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null)
            return Response.setFailure("User not existent");
        
        List<GoalGroup> goalGroups = goalGroupRepository.findAllByUserId(userId);

        if(goalGroups == null)
            return Response.setFailure("Failed to retrieve goal group");
        
        return Response.setSuccess("Successfully retrieved goal group", goalGroups);
    }

    public Response<GoalGroup> updateGoalGroup(UpdateGoalGroupRequest request)
    {
        Long targetUserId = request.getUserId();
        Long targetGoalGroupId = request.getGoalGroupId();
        String newTitle = request.getTitle();
        List<String> newApps = request.getApps();
        Long newTimeBudget = request.getTimeBudget();

        User targetUser = userRepository.findById(targetUserId).orElse(null);
        GoalGroup targetGoalGroup = goalGroupRepository.findById(targetGoalGroupId).orElse(null);

        if(targetUser == null)
            return Response.setFailure("User not existent");
        if(targetGoalGroup == null)
            return Response.setFailure("Goal group not existent");
        if(targetGoalGroup.getUser().getId() != targetUserId)
            return Response.setFailure("User does not own the goal group");

        if(newTitle == null || newTitle.isEmpty())
            return Response.setFailure("Goal group title cannot be null or empty");
        if(newApps == null)
            return Response.setFailure("Apps list can be empty but should not be null");
        if(newTimeBudget == null || newTimeBudget < 0)
            return Response.setFailure("Time budget cannot be null or negative");

        try
        {
            targetGoalGroup.setTitle(newTitle);
            targetGoalGroup.setTimeBudget(newTimeBudget);
    
            targetGoalGroup.getGoals().clear();
            for(String newApp: newApps)
            {
                Goal newGoal = new Goal(targetUser, targetGoalGroup, newApp);
                targetGoalGroup.addGoal(newGoal);
            }
    
            return Response.setSuccess("Successfully updated goal group", targetGoalGroup);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }

    public Response<?> removeGoalGroup(RemoveGoalGroupRequest request)
    {
        Long targetUserId = request.getUserId();
        Long targetGoalGroupId = request.getGoalGroupId();

        if(targetUserId == null)
            return Response.setFailure("User ID should not be null");
        if(targetGoalGroupId == null)
            return Response.setFailure("Goal Group ID should not be null");
        if(!userRepository.existsById(targetUserId))
            return Response.setFailure("User not existent with given ID");

        GoalGroup targetGoalGroup = goalGroupRepository.findById(targetGoalGroupId).orElse(null);
        if(targetGoalGroup == null)
            return Response.setFailure("Goal Group with given ID does not exist");
        
        if(targetGoalGroup.getUser().getId() != targetUserId)
            return Response.setFailure("User does not own the target goal group");

        try
        {
            goalGroupRepository.delete(targetGoalGroup);
            return Response.setSuccess("Successfully removed target goal group", null);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error");
        }
    }
}
