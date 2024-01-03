package club.pard.server.soonjji.sabotage.repository.phoneusage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club.pard.server.soonjji.sabotage.entity.phoneusage.PhoneUsage;

@Repository
public interface PhoneUsageRepository extends JpaRepository<PhoneUsage, Long>{
    
}
