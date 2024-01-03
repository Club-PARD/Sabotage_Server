package club.pard.server.soonjji.sabotage.entity.phoneusage;

import java.sql.Date;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Table(name = "phone_usage")
public class PhoneUsage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id") @Setter
    private User user;

    @Column(nullable = false)
    private Date usageDate;

    @Column(nullable = false)
    private Long usageRecorded;
}
