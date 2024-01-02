package club.pard.server.soonjji.sabotage.controller.ejection;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.ejection.EjectionRankResponse;
import club.pard.server.soonjji.sabotage.service.ejection.EjectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ejection")
public class EjectionController {
    private final EjectionService ejectionService;

    @Operation(summary = "Ejection/add: 특정 사용자의 탈출을 기록할 때 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created: 탈출이 정상적으로 기록됨을 의미함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User가 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<Response<?>> add(@PathVariable Long userId)
    {
        return ejectionService.add(userId);
    }

    @Operation(summary = "Ejection/getTodays: 특정 사용자의 (오늘의) 탈출 횟수를 조회할 때 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 사용자의 탈출 횟수가 정상적으로 조회됨을 의미함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User가 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @GetMapping("/{userId}/today")
    public ResponseEntity<Response<Long>> getTodays(@PathVariable Long userId)
    {
        Timestamp startOfToday = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay());
        return ejectionService.getTodays(userId, startOfToday);
    }

    @Operation(summary = "Ejection/getRank: (오늘) 사용자들이 탈출한 횟수를 특정 사용자의 목표 그룹 목록들을 조회할 때 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 사용자들의 (오늘) 탈출 횟수 순위가 정상적으로 조회됨을 의미함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @GetMapping("/rank")
    public ResponseEntity<Response<List<EjectionRankResponse>>> getRank()
    {
        Timestamp startOfToday = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atStartOfDay());
        return ejectionService.getRank(startOfToday);
    }
}
