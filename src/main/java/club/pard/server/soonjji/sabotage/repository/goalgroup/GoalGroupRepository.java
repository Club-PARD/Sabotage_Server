package club.pard.server.soonjji.sabotage.repository.goalgroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.goalgroup.GoalGroup;

public interface GoalGroupRepository extends JpaRepository<GoalGroup, Long> {
    public List<GoalGroup> findAllByUserId(Long userId);
}
