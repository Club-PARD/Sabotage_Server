package club.pard.server.soonjji.sabotage.repository.goalgroup;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.goal.GoalGroup;

public interface GoalGroupRepository extends JpaRepository<GoalGroup, Long> {
    
}
