package club.pard.server.soonjji.sabotage.repository.ejection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;

public interface EjectionRepository extends JpaRepository<Ejection, Long> {
    public Long countByUserIdAndTimeOccurredGreaterThanEqual(Long userId, Timestamp targetTimestamp);

    @Query("select new club.pard.server.soonjji.sabotage.repository.ejection.EjectionRank(rank() over (order by count(e.user) desc), e.user.id, count(e.user)) from Ejection e where e.timeOccurred >= :timeCriteria group by e.user order by count(e.user) desc")
    public List<EjectionRank> getRankList(Timestamp timeCriteria);
}
