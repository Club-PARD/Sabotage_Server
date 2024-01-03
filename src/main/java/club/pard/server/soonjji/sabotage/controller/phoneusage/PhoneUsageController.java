package club.pard.server.soonjji.sabotage.controller.phoneusage;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.phoneusage.AddPhoneUsageRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageComparisonDailyResponse;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageComparisonWeeklyResponse;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.phoneusage.PhoneUsageService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/phoneUsage")
@RequiredArgsConstructor
public class PhoneUsageController {
    private final PhoneUsageService phoneUsageService;

    @PutMapping("/{userId}")
    public ResponseEntity<Response<PhoneUsageSimplifiedResponse>> add(@RequestBody AddPhoneUsageRequest request)
    {
        return phoneUsageService.add(request);
    }

    @GetMapping("/{userId}/daily")
    public ResponseEntity<Response<PhoneUsageComparisonDailyResponse>> getAnalysisDaily(@PathVariable Long userId, @RequestParam("date") String date)
    {
        return phoneUsageService.getAnalysisDaily(userId, date);
    }

    @GetMapping("/{userId}/weekly")
    public ResponseEntity<Response<PhoneUsageComparisonWeeklyResponse>> getAnalysisWeekly(@PathVariable Long userId, @RequestParam("date") String date)
    {
        return phoneUsageService.getAnalysisWeekly(userId, date);
    }
}
