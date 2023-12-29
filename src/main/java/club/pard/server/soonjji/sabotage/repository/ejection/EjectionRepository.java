package club.pard.server.soonjji.sabotage.repository.ejection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;

public interface EjectionRepository extends JpaRepository<Ejection, Long> {
    @Query("select e from Ejection e where e.timeOccurred >= ?1")
    public List<Ejection> findAllEjectionsFrom(Timestamp targetTimestamp);
}
