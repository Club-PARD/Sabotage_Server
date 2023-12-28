package club.pard.server.soonjji.sabotage.entity.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import club.pard.server.soonjji.sabotage.converter.PasswordEncryptConverter;
import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;
import club.pard.server.soonjji.sabotage.entity.appusage.AppUsage;
import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;
import club.pard.server.soonjji.sabotage.entity.thresholdgroup.ThresholdGroup;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Convert(converter = PasswordEncryptConverter.class)
    @Column(unique = true, nullable = false)
    private String deviceId;

    Timestamp dateDeactivated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThresholdGroup> thresholdGroups = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppUsage> appUsages = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejection> ejections = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActionItem> actionItems = new ArrayList<>();

    public User(String deviceId, String nickname)
    {
        this.deviceId = deviceId;
        this.nickname = nickname;
    }
}
