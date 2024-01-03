package club.pard.server.soonjji.sabotage.dto.response.phoneusage;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneUsageComparisonWeeklyResponse {
    private final Long userId;
    private final Date date;
    private final List<Long> timesUsedWeekly;
}
