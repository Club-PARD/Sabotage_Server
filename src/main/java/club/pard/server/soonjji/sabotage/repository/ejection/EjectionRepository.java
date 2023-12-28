package club.pard.server.soonjji.sabotage.repository.ejection;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;

public interface EjectionRepository extends JpaRepository<Ejection, Long> {
    
}
