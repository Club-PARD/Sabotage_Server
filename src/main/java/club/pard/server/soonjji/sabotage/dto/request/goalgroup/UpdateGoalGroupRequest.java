package club.pard.server.soonjji.sabotage.dto.request.goalgroup;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoalGroupRequest {
    private Long userId;
    private Long goalGroupId;
    private String title;
    private List<String> apps;
    private Long timeBudget;
}
