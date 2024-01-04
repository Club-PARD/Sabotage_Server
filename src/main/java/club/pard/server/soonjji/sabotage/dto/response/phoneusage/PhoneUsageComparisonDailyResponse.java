package club.pard.server.soonjji.sabotage.dto.response.phoneusage;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "전날 - 당일 간 사용 시간 비교 시 받게 되는 Response")
@Getter
@AllArgsConstructor
public class PhoneUsageComparisonDailyResponse {
    @Schema(description = "사용 시간 기록 소유자의 사용자 ID 번호")
    private final Long userId;

    @Schema(description = "비교하는 날짜 중 당일의 날짜")
    private final Date date;

    @Schema(description = "비교하는 날짜 중 전날에 사용한 시간(분 단위)")
    private final Long timeUsedYesterday;

    @Schema(description = "비교하는 날짜 중 당일에 사용한 시간(분 단위)")
    private final Long timeUsedToday;
}
