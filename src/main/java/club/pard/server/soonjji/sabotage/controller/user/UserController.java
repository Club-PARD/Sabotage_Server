package club.pard.server.soonjji.sabotage.controller.user;

import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.user.AddUserRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.user.UserSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "User/add: 사용자를 추가할 경우 사용하는 API Call", description = "사용자의 device ID를 Body에 문자열 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 사용자가 정상적으로 추가됨을 의미함"),
        @ApiResponse(responseCode = "400", description = "Bad Request: 같은 device ID를 사용하는 사용자가 존재할 경우 발생함", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Response<UserSimplifiedResponse>> add(@RequestBody @Valid final AddUserRequest request)
    {
        return userService.add(request);
    }

    @Operation(summary = "User/list: 사용자의 정보를 조회할 경우 사용하는 API Call", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 사용자를 정상적으로 조회함을 의미함"),
        @ApiResponse(responseCode = "400", description = "Bad Request: 여기선 안 다루지만 GlobalExceptionHandler에서 전역으로 다루는 Response", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User가 존재하지 않을 경우 발생함", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함", content = @Content)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<Response<UserSimplifiedResponse>> list(@Parameter(description = "정보를 조회할 대상 사용자의 ID", required = true) @PathVariable Long userId)
    {
        return userService.list(userId);
    }
}