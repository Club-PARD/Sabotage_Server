package club.pard.server.soonjji.sabotage.controller.phoneusage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.dto.request.phoneusage.AddPhoneUsageRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageSimplifiedResponse;
import club.pard.server.soonjji.sabotage.service.phoneusage.PhoneUsageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/phoneUsage")
@RequiredArgsConstructor
public class PhoneUsageController {
    private final PhoneUsageService phoneUsageService;

    @PutMapping("/{userId}")
    public ResponseEntity<Response<PhoneUsageSimplifiedResponse>> add(AddPhoneUsageRequest request)
    {
        return phoneUsageService.add(request);
    }
}
