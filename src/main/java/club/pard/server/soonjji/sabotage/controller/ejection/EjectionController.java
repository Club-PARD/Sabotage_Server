package club.pard.server.soonjji.sabotage.controller.ejection;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.ejection.EjectionRankResponse;
import club.pard.server.soonjji.sabotage.service.ejection.EjectionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ejection")
public class EjectionController {
    private final EjectionService ejectionService;

    @PostMapping("/{userId}")
    public Response<?> add(@PathVariable Long userId)
    {
        return ejectionService.add(userId);
    }

    @GetMapping("/{userId}/today")
    public Response<Long> getTodays(@PathVariable Long userId)
    {
        Timestamp startOfToday = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay());
        return ejectionService.getTodays(userId, startOfToday);
    }

    @GetMapping("/rank")
    public Response<List<EjectionRankResponse>> getRank()
    {
        Timestamp startOfToday = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay());
        return ejectionService.getRank(startOfToday);
    }
}
