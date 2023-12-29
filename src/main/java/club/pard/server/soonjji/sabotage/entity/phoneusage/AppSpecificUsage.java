package club.pard.server.soonjji.sabotage.entity.phoneusage;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_specific_usage")
public class AppSpecificUsage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String appId;

    @CreationTimestamp
    private Timestamp timeUploaded;

    @Column(nullable = false)
    private Long secondsUsed;

    @Column(nullable = false)
    private Timestamp timeStarted;
}
