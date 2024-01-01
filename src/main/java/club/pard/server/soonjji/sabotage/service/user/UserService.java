package club.pard.server.soonjji.sabotage.service.user;

import java.util.Random;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.user.UserSimplifiedResponse;
import club.pard.server.soonjji.sabotage.entity.user.User;
import club.pard.server.soonjji.sabotage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private static final String[] adjectives = new String[]{"꾸준한", "낙천적인", "대담한", "매력적인", "바른", "순수한", "우아한", "지혜로운", "침착한", "털털한", "포근한", "활발한"};
    private static final String[] nouns = new String[]{"곰", "두루미", "고양이", "강아지", "거위", "얼룩말", "돌고래", "알파카", "다람쥐", "앵무새", "너구리", "호랑이"};

    public Response<UserSimplifiedResponse> register(String deviceId)
    {
        try
        {
            if(userRepository.existsByDeviceId(deviceId))
                return Response.setFailure("해당 기기로 등록된 사용자가 이미 있어요!", "User/register: deviceId exists");

            Random rng = new Random();
            String newNickname;
            do
            {
                newNickname = new String(
                    adjectives[rng.nextInt(adjectives.length)] +
                    nouns[rng.nextInt(nouns.length)] +
                    String.format("%04d", rng.nextInt(10000))
                );
            }while(userRepository.existsByNickname(newNickname));

            User newUser = new User(deviceId, newNickname);
            userRepository.save(newUser);

            return Response.setSuccess("사용자 등록 완료!", "User/register: new User added", new UserSimplifiedResponse(newUser));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버에 오류가 생겼어요!", "User/register: Internal Server Error");
        }
    }
}
