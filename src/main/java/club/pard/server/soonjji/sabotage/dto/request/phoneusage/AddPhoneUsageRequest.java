package club.pard.server.soonjji.sabotage.dto.request.phoneusage;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddPhoneUsageRequest {
    @NotNull @Positive
    private Long userId;
    
    @NotNull @PastOrPresent
    private Date date;

    @NotNull @PositiveOrZero
    private Long timeUsed;
}
