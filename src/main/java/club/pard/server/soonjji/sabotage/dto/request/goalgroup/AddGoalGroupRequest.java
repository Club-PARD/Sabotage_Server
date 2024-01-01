package club.pard.server.soonjji.sabotage.dto.request.goalgroup;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddGoalGroupRequest {
    @NotBlank(message = "Title should not be null or empty")
    private String title;
    @NotNull(message = "Apps list should not be null")
    private List<String> apps;
    @NotNull(message = "Time budget should not be null") @Positive(message = "Time budget should be positive")
    private Long timeBudget;
}
