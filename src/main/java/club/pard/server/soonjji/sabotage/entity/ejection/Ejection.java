package club.pard.server.soonjji.sabotage.entity.ejection;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import club.pard.server.soonjji.sabotage.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ejection")
@Getter
@ToString
@NoArgsConstructor
public class Ejection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp timeOccurred;
}
