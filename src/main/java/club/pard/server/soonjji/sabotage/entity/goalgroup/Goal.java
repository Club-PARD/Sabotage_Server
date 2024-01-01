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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "goal")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @Setter @ManyToOne @JoinColumn(name = "goal_group_id")
    private GoalGroup goalGroup;

    @Column(nullable = false)
    private String appId;

    @Setter @Column(nullable = false)
    private Long timeBudget;

    @Builder
    public Goal(String appId, Long timeBudget)
    {
        this.appId = appId;
        this.timeBudget = timeBudget;
    }
}
