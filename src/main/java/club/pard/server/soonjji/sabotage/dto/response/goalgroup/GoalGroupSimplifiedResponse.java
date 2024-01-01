package club.pard.server.soonjji.sabotage.dto.response.goalgroup;

import java.util.ArrayList;
import java.util.List;

import club.pard.server.soonjji.sabotage.entity.goalgroup.Goal;
import club.pard.server.soonjji.sabotage.entity.goalgroup.GoalGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoalGroupSimplifiedResponse {
    private Long id;
    private Long userId;
    private String title;
    private List<String> apps;
    private Long timeBudget;

    public static GoalGroupSimplifiedResponse from(GoalGroup goalGroup){
        GoalGroupSimplifiedResponse response = new GoalGroupSimplifiedResponse();
        response.setId(goalGroup.getId());
        response.setUserId(goalGroup.getUser().getId());
        response.setTitle(goalGroup.getTitle());
        response.setTimeBudget(goalGroup.getTimeBudget());

        response.setApps(new ArrayList<>());
        for(Goal goal: goalGroup.getGoals())
            response.getApps().add(goal.getAppId());
        
        return response;
    }
}