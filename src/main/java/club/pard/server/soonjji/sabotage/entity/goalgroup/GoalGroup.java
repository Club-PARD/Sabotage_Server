package club.pard.server.soonjji.sabotage.entity.goalgroup;

import java.util.ArrayList;
import java.util.List;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "goal_group")
public class GoalGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "goalGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    @Column(nullable = false)
    private Long timeBudget;

    public GoalGroup(User user, String title, Long timeBudget)
    {
        this.user = user;
        this.title = title;
        this.timeBudget = timeBudget;
    }

    public void addGoal(Goal goal)
    {
        this.goals.add(goal);
        goal.setGoalGroup(this);
        goal.setTimeBudget(this.timeBudget);
    }
}
