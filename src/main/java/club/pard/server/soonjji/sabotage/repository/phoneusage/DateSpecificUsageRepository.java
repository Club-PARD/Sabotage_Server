package club.pard.server.soonjji.sabotage.repository.phoneusage;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.phoneusage.DateSpecificUsage;

public interface DateSpecificUsageRepository extends JpaRepository<DateSpecificUsage, Long> {
    
}
