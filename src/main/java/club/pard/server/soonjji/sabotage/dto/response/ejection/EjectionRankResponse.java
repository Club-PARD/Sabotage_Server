package club.pard.server.soonjji.sabotage.dto.response.ejection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EjectionRankResponse {
    private String nickname;
    private Long ejectionCount;

    public EjectionRankResponse(String nickname, Long ejectionCount){ this.nickname = nickname; this.ejectionCount = ejectionCount; }
}
