package club.pard.server.soonjji.sabotage.controller.ejection;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.Response.Response;
import club.pard.server.soonjji.sabotage.service.ejection.EjectionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ejection")
public class EjectionController {
    private final EjectionService ejectionService;

    @PostMapping("/{userId}")
    public Response<?> addEjection(@PathVariable Long userId)
    {
        return ejectionService.addEjection(userId);
    }

    @GetMapping("/{userId}")
    public Response<Integer> getEjectionsFrom(@PathVariable Long userId)
    {
        Timestamp startOfToday = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay());
        return ejectionService.getEjectionsCount(startOfToday);
    }
}
