package club.pard.server.soonjji.sabotage.repository.ejection;

import lombok.Getter;

@Getter
public class EjectionRank {
    private Long userId;
    private Long ejectionCount;

    public EjectionRank(Long userId, Long ejectionCount){ this.userId = userId; this.ejectionCount = ejectionCount; }
}
