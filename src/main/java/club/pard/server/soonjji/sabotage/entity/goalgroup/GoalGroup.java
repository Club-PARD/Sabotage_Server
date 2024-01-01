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
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "goal_group")
@Getter
@ToString
@NoArgsConstructor
public class GoalGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @Setter @Column(nullable = false)
    private String title;

    @Setter @OneToMany(mappedBy = "goalGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    @Setter @Column(nullable = false)
    private Long timeBudget;


    @Builder
    public GoalGroup(String title, Long timeBudget)
    {
        this.title = title;
        this.timeBudget = timeBudget;
    }

    public void addGoal(Goal goal)
    {
        this.goals.add(goal);
        goal.setUser(this.getUser());
        goal.setGoalGroup(this);
        goal.setTimeBudget(this.timeBudget);
    }
}
