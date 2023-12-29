package club.pard.server.soonjji.sabotage.repository.goalgroup;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.goal.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    public void findAllByUserIdAndAppId(Long userId, String appId);
}
