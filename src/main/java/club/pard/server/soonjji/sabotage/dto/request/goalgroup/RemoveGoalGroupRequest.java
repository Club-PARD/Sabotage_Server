package club.pard.server.soonjji.sabotage.dto.request.goalgroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveGoalGroupRequest {
    private Long userId;
    private Long goalGroupId;    
}
