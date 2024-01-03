package club.pard.server.soonjji.sabotage.dto.response.phoneusage;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneUsageComparisonDailyResponse {
    private final Long userId;
    private final Date date;
    private final Long timeUsedYesterday;
    private final Long timeUsedToday;
}
