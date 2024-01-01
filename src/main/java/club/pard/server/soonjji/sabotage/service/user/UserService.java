package club.pard.server.soonjji.sabotage.service.user;

import java.sql.Timestamp;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.soonjji.sabotage.dto.request.user.AddUserRequest;
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

    @Transactional
    public ResponseEntity<Response<UserSimplifiedResponse>> add(AddUserRequest request)
    {
        try
        {
            String deviceId = request.getDeviceId();
            if(userRepository.existsByDeviceId(deviceId))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.setFailure("해당 기기로 등록된 사용자가 이미 있어요!", "User/register: deviceId exists"));

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

            System.out.println(deviceId + " / " + newNickname);
            User newUser = User.builder().deviceId(deviceId).nickname(newNickname).build();
            userRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("사용자 등록 완료!", "User/register: new User added", UserSimplifiedResponse.from(newUser)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버에 오류가 생겼어요!", "User/register: Internal Server Error"));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Response<UserSimplifiedResponse>> list(Long userId)
    {
        try
        {
            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.setFailure("해당 사용자가 존재하지 않아요!", "User/list: Target User not existent"));
            
            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("사용자 조회 완료!", "User/list: successful", UserSimplifiedResponse.from(targetUser)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버에 오류가 생겼어요!", "User/list: Internal Server Error"));
        }
    }

    @Transactional
    public ResponseEntity<Response<Timestamp>> deactivate(Long userId)
    {
        try
        {   
            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.setFailure("해당 ID를 가지는 사용자가 없어요!", "User/deactivate: User not existent with given ID"));

            Timestamp timeDeactivated = new Timestamp(System.currentTimeMillis());
            targetUser.setDateDeactivated(timeDeactivated);

            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("비활성화 완료!", "User/deactivate: User deactivation time set", timeDeactivated));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버에 오류가 생겼어요!", "User/deactivate: Internal Server Error"));
        }
    }
}
