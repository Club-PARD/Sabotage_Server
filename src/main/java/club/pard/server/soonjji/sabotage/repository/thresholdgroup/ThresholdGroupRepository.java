package club.pard.server.soonjji.sabotage.repository.thresholdgroup;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.thresholdgroup.ThresholdGroup;

public interface ThresholdGroupRepository extends JpaRepository<ThresholdGroup, Long> {
    
}
