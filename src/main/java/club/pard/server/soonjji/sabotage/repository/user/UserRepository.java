package club.pard.server.soonjji.sabotage.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public boolean existsByNickname(String targetNickname);
    public boolean existsByDeviceId(String targetDeviceId);
}
