package club.pard.server.soonjji.sabotage.controller.goalgroup;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.goalgroup.AddGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.request.goalgroup.UpdateGoalGroupRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.goalgroup.GoalGroupSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.goalgroup.GoalGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/goalGroup")
@RequiredArgsConstructor
public class GoalGroupController {
    private final GoalGroupService goalGroupService;

    @Operation(summary = "GoalGroup/add: 목표 그룹 추가 시 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로, 추가할 목표 그룹의 내용을 Body로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 목표 그룹이 정상적으로 추가됨을 의미함"),
        @ApiResponse(responseCode = "400", description = "Bad Request: 목표 그룹 추가 시 이름이나 앱 리스트가 비어 있거나 목록이 존재하지 않을 경우, 혹은 목표 시간이 비어 있거나 양의 정수가 아닐 경우 발생함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User가 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<Response<GoalGroupSimplifiedResponse>> add(@PathVariable Long userId, @RequestBody @Valid AddGoalGroupRequest request)
    {
        return goalGroupService.add(userId, request);
    }

    @Operation(summary = "GoalGroup/list: 특정 사용자의 목표 그룹 목록들을 조회할 때 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 목표 그룹 목록이 정상적으로 조회됨을 의미함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User가 존재하지 않거나 User에 속한 목표 그룹 리스트가 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<Response<List<GoalGroupSimplifiedResponse>>> list(@PathVariable Long userId)
    {
        return goalGroupService.list(userId);
    }

    @Operation(summary = "GoalGroup/list: 특정 사용자의 목표 그룹 목록들을 조회할 때 사용하는 함수", description = "userId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 목표 그룹이 정상적으로 수정됨을 의미함"),
        @ApiResponse(responseCode = "400", description = "Bad Request: Body에 넣어준 (수정할) 목표 그룹 내용 중 이름이나 앱 리스트가 비어있거나 존재하지 않을 경우, 혹은 목표 시간이 비어 있거나 양의 정수가 아닐 경우 발생함"),
        @ApiResponse(responseCode = "403", description = "Forbidden: Path Variable로 명시한 User가 목표 그룹을 소유하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User나 목표 그룹이 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @PatchMapping("/{userId}/{goalGroupId}")
    public ResponseEntity<Response<GoalGroupSimplifiedResponse>> update(@PathVariable Long userId, @PathVariable Long goalGroupId, @RequestBody @Valid UpdateGoalGroupRequest request)
    {
        return goalGroupService.update(userId, goalGroupId, request);
    }

    @Operation(summary = "GoalGroup/remove: 목표 그룹을 삭제할 때 사용하는 함수", description = "userId와 goalGroupId를 URL 안에 Path Variable로써 정수 형태로 입력받는다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK: 목표 그룹이 정상적으로 삭제됨을 의미함"),
        @ApiResponse(responseCode = "403", description = "Forbidden: Path Variable로 명시한 User가 목표 그룹을 소유하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "404", description = "Not Found: Path Variable로 명시한 User나 목표 그룹이 존재하지 않을 경우 발생함"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error: 서버에 다루지 못한 Exception이 발생, 예외 스택이 출력될 것이니 서버 관리자가 확인해야 함")
    })
    @DeleteMapping("/{userId}/{goalGroupId}")
    public ResponseEntity<Response<?>> remove(@PathVariable Long userId, @PathVariable Long goalGroupId)
    {
        return goalGroupService.remove(userId, goalGroupId);
    }
}
