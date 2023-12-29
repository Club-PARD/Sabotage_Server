package club.pard.server.soonjji.sabotage.repository.phoneusage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.phoneusage.AppSpecificUsage;

public interface AppSpecificUsageRepository extends JpaRepository<AppSpecificUsage, Long> {
    public List<AppSpecificUsage> findByUserIdOrderByTimeUsedDesc(Long userId);
}
