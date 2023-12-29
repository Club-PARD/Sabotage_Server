package club.pard.server.soonjji.sabotage.service.phoneusage;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.repository.phoneusage.AppSpecificUsageRepository;
import club.pard.server.soonjji.sabotage.repository.phoneusage.DateSpecificUsageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneUsageService {
    private final AppSpecificUsageRepository appSpecificUsageRepository;
    private final DateSpecificUsageRepository dateSpecificUsageRepository;

    public void addPhoneUsage()
}
