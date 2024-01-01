package club.pard.server.soonjji.sabotage.service.ejection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.entity.ejection.Ejection;
import club.pard.server.soonjji.sabotage.entity.user.User;
import club.pard.server.soonjji.sabotage.repository.ejection.EjectionRepository;
import club.pard.server.soonjji.sabotage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EjectionService {
    private final EjectionRepository ejectionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Response<?> add(Long userId)
    {
        try
        {
            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return Response.setFailure("해당 사용자가 존재하지 않아요!", "Ejection/add: Target User not existent");
           
            
            Ejection newEjection = new Ejection();
            ejectionRepository.save(newEjection);
            targetUser.addEjection(newEjection);

            return Response.setSuccess("탈출 추가 완료!", "Ejection/add: Successful", null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버에 오류가 생겼어요!", "Ejection/add: Internal Server Error");
        }
    }

    @Transactional
    public Response<Long> getTodays(Long userId, Timestamp targetTimestamp)
    {
        try
        {
            if(!userRepository.existsById(userId))
                return Response.setFailure("해당 사용자가 존재하지 않아요!", "Ejection/getTodays: Target User not existent");
            // List<Ejection> ejections = ejectionRepository.findAllByUserIdAndTimeOccurredGreaterThanEqual(userId, targetTimestamp);
            Long ejections = ejectionRepository.countByUserIdAndTimeOccurredGreaterThanEqual(userId, targetTimestamp);
    
            return Response.setSuccess("탈출 목록 조회 완료!", "Ejection/getTodays: Successful", ejections);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Response.setFailure("서버에 오류가 생겼어요!", "Ejection/getTodays: Internal Server Error");
        }
    }
}
