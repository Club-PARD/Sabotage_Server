package club.pard.server.soonjji.sabotage.repository.goalgroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.goalgroup.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    public List<Goal> findAllByUserIdAndGoalGroupId(Long userId, Long goalGroupId);
    public boolean existsByUserIdAndAppId(Long userId, String appId);
}
