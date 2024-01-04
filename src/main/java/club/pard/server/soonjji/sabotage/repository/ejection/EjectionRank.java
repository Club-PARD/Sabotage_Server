package club.pard.server.soonjji.sabotage.repository.ejection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EjectionRank {
    private Long rank;
    private Long userId;
    // private String userNickname;
    private Long ejectionCount;

    // public EjectionRank(Long rank, String userNickname, Long ejectionCount){ this.rank = rank; this.userNickname = userNickname; this.ejectionCount = ejectionCount; }
    public EjectionRank(Long rank, Long userId, Long ejectionCount){ this.rank = rank; this.userId = userId; this.ejectionCount = ejectionCount; }
}
