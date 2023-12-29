package club.pard.server.soonjji.sabotage.entity.constraintgroup;

import java.util.ArrayList;
import java.util.List;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "constraint_group")
public class ConstraintGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> apps = new ArrayList<>();

    private int timeBudget;
}
