package club.pard.server.soonjji.sabotage.controller.phoneusage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.soonjji.sabotage.service.phoneusage.PhoneUsageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/phoneUsage")
@RequiredArgsConstructor
public class PhoneUsageController {
    private final PhoneUsageService appUsageService;
}
