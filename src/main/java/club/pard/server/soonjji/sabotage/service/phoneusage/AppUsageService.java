package club.pard.server.soonjji.sabotage.service.phoneusage;

import java.util.List;

import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.request.appusage.GetTop3AppUsageRequest;
import club.pard.server.soonjji.sabotage.entity.phoneusage.AppSpecificUsage;
import club.pard.server.soonjji.sabotage.repository.phoneusage.AppSpecificUsageRepository;
import club.pard.server.soonjji.sabotage.repository.goalgroup.GoalGroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUsageService {
    private final AppSpecificUsageRepository appUsageRepository;
    private final GoalGroupRepository goalGroupRepository;

    public List<AppSpecificUsage> getTop3AppUsage(GetTop3AppUsageRequest request)
    {
        return null;
    }
}
