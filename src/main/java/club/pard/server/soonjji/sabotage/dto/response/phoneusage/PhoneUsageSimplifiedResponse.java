package club.pard.server.soonjji.sabotage.dto.response.phoneusage;

import java.sql.Date;

import club.pard.server.soonjji.sabotage.entity.phoneusage.PhoneUsage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Schema(description = "사용 시간에서 User 정보를 간소화한 Response")
@Getter
@ToString
@RequiredArgsConstructor
public class PhoneUsageSimplifiedResponse {
    @Schema(description = "사용 시간 ID")
    private final Long id;

    @Schema(description = "사용 시간 기록 소유자의 사용자 ID 번호")
    private final Long userId;

    @Schema(description = "기록한 사용 시간의 날짜")
    private final Date date;

    @Schema(description = "기록한 사용 시간(분 단위)")
    private final Long timeUsed;

    public static PhoneUsageSimplifiedResponse from(PhoneUsage phoneUsage){ return new PhoneUsageSimplifiedResponse(phoneUsage.getId(), phoneUsage.getUser().getId(), phoneUsage.getDate(), phoneUsage.getTimeUsed()); }
}
