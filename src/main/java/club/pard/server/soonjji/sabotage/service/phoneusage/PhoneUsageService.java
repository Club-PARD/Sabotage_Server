package club.pard.server.soonjji.sabotage.service.phoneusage;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import club.pard.server.soonjji.sabotage.dto.request.phoneusage.AddPhoneUsageRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageSimplifiedResponse;
import club.pard.server.soonjji.sabotage.repository.phoneusage.PhoneUsageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneUsageService {
    private final PhoneUsageRepository phoneUsageRepository;

    public ResponseEntity<Response<PhoneUsageSimplifiedResponse>> add(AddPhoneUsageRequest request)
    {
        return null;
    }
}
