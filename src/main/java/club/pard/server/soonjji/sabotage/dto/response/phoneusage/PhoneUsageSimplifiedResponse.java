package club.pard.server.soonjji.sabotage.dto.response.phoneusage;

import java.sql.Date;

import club.pard.server.soonjji.sabotage.entity.phoneusage.PhoneUsage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PhoneUsageSimplifiedResponse {
    private final Long id;
    private final Long userId;
    private final Date date;
    private final Long timeUsed;

    public static PhoneUsageSimplifiedResponse from(PhoneUsage phoneUsage){ return new PhoneUsageSimplifiedResponse(phoneUsage.getId(), phoneUsage.getUser().getId(), phoneUsage.getDate(), phoneUsage.getTimeUsed()); }
}
