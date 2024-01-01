package club.pard.server.soonjji.sabotage.repository.ejection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;

public interface EjectionRepository extends JpaRepository<Ejection, Long> {
    // @Query("select e from Ejection e where e.timeOccurred >= ?2 and e.user = ?1")
    // public List<Ejection> findAllEjectionsByUserIdFrom(Long userId, Timestamp targetTimestamp);

    // public List<Ejection> findAllByUserIdAndTimeOccurredGreaterThanEqual(Long userId, Timestamp targetTimestamp);
    public Long countByUserIdAndTimeOccurredGreaterThanEqual(Long userId, Timestamp targetTimestamp);

    @Query("select new club.pard.server.soonjji.sabotage.repository.ejection.EjectionRank(e.user.id, count(e.user)) from Ejection e where e.timeOccurred >= :timeCriteria group by e.user order by count(e.user) desc limit 10")
    public List<EjectionRank> getRank(Timestamp timeCriteria);
}
