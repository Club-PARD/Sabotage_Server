package club.pard.server.soonjji.sabotage.dto.request.appusage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTop3AppUsageRequest {
    private Long userId;
    private Long goalGroupId;
}
