package club.pard.server.soonjji.sabotage.repository.appusage;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.appusage.AppUsage;

public interface AppUsageRepository extends JpaRepository<AppUsage, Long> {
    
}
