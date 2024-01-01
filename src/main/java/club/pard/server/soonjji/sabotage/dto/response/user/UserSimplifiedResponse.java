package club.pard.server.soonjji.sabotage.dto.response.user;

import club.pard.server.soonjji.sabotage.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSimplifiedResponse {
    private final Long id;
    private final String nickname;

    public UserSimplifiedResponse(User user){ this.id = user.getId(); this.nickname = user.getNickname(); }
}
