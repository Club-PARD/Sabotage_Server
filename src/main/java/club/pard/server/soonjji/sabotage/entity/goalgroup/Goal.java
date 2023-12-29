package club.pard.server.soonjji.sabotage.entity.goalgroup;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "goal")
public class Goal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "goal_group_id")
    private GoalGroup goalGroup;

    @Column(nullable = false)
    private String appId;

    @Setter @Column(nullable = false)
    private Long timeBudget;

    public Goal(User user, GoalGroup group, String appId)
    {
        this.user = user;
        this.goalGroup = group;
        this.appId = appId;
    }
}
