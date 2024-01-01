package club.pard.server.soonjji.sabotage.service.ejection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

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

    public Response<?> addEjection(Long userId)
    {
        try
        {
            User owner = userRepository.findById(userId).orElse(null);
            if(owner == null)
                return Response.setFailure("Invalid user ID", "");
           
            
            Ejection newEjection = new Ejection(owner);
            ejectionRepository.save(newEjection);

            return Response.setSuccess("Successfully added ejection", "", null);
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error", "");
        }
    }

    public Response<Integer> getEjectionsCount(Timestamp targetTimestamp)
    {
        try
        {
            List<Ejection> ejections = ejectionRepository.findAllEjectionsFrom(targetTimestamp);
    
            return Response.setSuccess("Successfully retrieved ejections list", "", ejections.size());
        }
        catch(Exception e)
        {
            return Response.setFailure("Internal DB Error", "");
        }
    }
}
