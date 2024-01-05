package club.pard.server.soonjji.sabotage.service.phoneusage;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.soonjji.sabotage.dto.request.phoneusage.AddPhoneUsageRequest;
import club.pard.server.soonjji.sabotage.dto.response.Response;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageComparisonDailyResponse;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageComparisonWeeklyResponse;
import club.pard.server.soonjji.sabotage.dto.response.phoneusage.PhoneUsageSimplifiedResponse;
import club.pard.server.soonjji.sabotage.entity.phoneusage.PhoneUsage;
import club.pard.server.soonjji.sabotage.entity.user.User;
import club.pard.server.soonjji.sabotage.repository.phoneusage.PhoneUsageRepository;
import club.pard.server.soonjji.sabotage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneUsageService {
    private final PhoneUsageRepository phoneUsageRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Response<PhoneUsageSimplifiedResponse>> add(Long userId, AddPhoneUsageRequest request)
    {
        try
        {
            log.debug(String.format("Received call `PUT /api/phoneUsage/%d` with %s", userId, request.toString()));


            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.setFailure("해당 사용자가 존재하지 않아요!", "PhoneUsage/add: Target User not existent"));

            Date date = request.getDate();
            Long timeUsed = request.getTimeUsed();

            if(date == null || date.compareTo(new Date(System.currentTimeMillis())) > 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.setFailure("사용 날짜가 존재하지 않거나 지나가지 않은 날짜에요!", "PhoneUsage/add: Usage Date is null or of future time"));
            if(timeUsed == null || timeUsed < 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.setFailure("사용 시간이 존재하지 않거나 음수에요!", "PhoneUsage/add: Usage time is null or negative"));
            
            PhoneUsage phoneUsage = phoneUsageRepository.findByUserIdAndDate(timeUsed, date).orElse(null);
            if(phoneUsage == null)
            {
                phoneUsage = PhoneUsage.builder().date(date).timeUsed(timeUsed).build();
                targetUser.addPhoneUsage(phoneUsage);
                phoneUsageRepository.save(phoneUsage);
            }
            else
                phoneUsage.setTimeUsed(timeUsed);

            
            PhoneUsageSimplifiedResponse response = PhoneUsageSimplifiedResponse.from(phoneUsage);
            log.debug(String.format("Answered call `PUT /api/phoneUsage/%d` successfully with %s", userId, response.toString()));

            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("사용 기록 추가 완료!", "PhoneUsage/add: Successful", response));

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버 내부에 오류가 생겼어요!", "PhoneUsage/add: Internal Server Error"));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Response<PhoneUsageComparisonDailyResponse>> getAnalysisDaily(Long userId, String targetDateString)
    {
        try
        {
            log.debug(String.format("Received call `GET /api/phoneUsage/%d/daily` with date %s", userId, targetDateString));


            Date targetDate = Date.valueOf(targetDateString);
            
            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.setFailure("해당 사용자가 존재하지 않아요!", "PhoneUsage/getAnalysisDaily: Target User not existent"));

            if(targetDate == null || targetDate.compareTo(new Date(System.currentTimeMillis())) > 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.setFailure("사용 날짜를 확인할 수 없거나 지나가지 않은 날짜에요!", "PhoneUsage/getAnalysisDaily: Date is null or of future time"));
            
            Date yesterday = Date.valueOf(targetDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate()
                .minusDays(1));

            PhoneUsage phoneUsageToday = phoneUsageRepository.findByUserIdAndDate(userId, targetDate).orElse(null);
            Long timeUsedToday = (phoneUsageToday == null) ? 0 : phoneUsageToday.getTimeUsed();

            PhoneUsage phoneUsageYesterday = phoneUsageRepository.findByUserIdAndDate(userId, yesterday).orElse(null);
            Long timeUsedYesterday = (phoneUsageYesterday == null) ? 0 : phoneUsageYesterday.getTimeUsed();

            Long timeUsedDifferenceRate = Math.round((timeUsedToday - timeUsedYesterday) / timeUsedYesterday.doubleValue() * 100);


            PhoneUsageComparisonDailyResponse response = new PhoneUsageComparisonDailyResponse(userId, targetDate, timeUsedYesterday, timeUsedToday, timeUsedDifferenceRate);
            log.debug(String.format("Answered call `GET /api/phoneUsage/%d/daily` at date %s successfully with %s", userId, targetDateString, response.toString()));

            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("어제 오늘 사용 시간 조회 성공!", "PHoneUsage/getAnalysisDaily: Successful", response));
        }
        catch(Exception e)
        {
            e.printStackTrace();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버 내부에 오류가 생겼어요!", "PhoneUsage/getAnalysisDaily: Internal Server Error"));
        }
    }
    
    @Transactional(readOnly = true)
    public ResponseEntity<Response<PhoneUsageComparisonWeeklyResponse>> getAnalysisWeekly(Long userId, String targetDateString)
    {
        try
        {
            log.debug(String.format("Received call `GET /api/phoneUsage/%d/weekly` with date %s", userId, targetDateString));


            Date targetDate = Date.valueOf(targetDateString);

            User targetUser = userRepository.findById(userId).orElse(null);
            if(targetUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.setFailure("해당 사용자가 존재하지 않아요!", "PhoneUsage/getAnalysisDaily: Target User not existent"));

            if(targetDate == null || targetDate.compareTo(new Date(System.currentTimeMillis())) > 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.setFailure("사용 날짜를 확인할 수 없거나 지나가지 않은 날짜에요!", "PhoneUsage/getAnalysisWeekly: Date is null or of future time"));
            
            List<Long> timeUsedWeekly = new ArrayList<>();
            int dayOfWeek = targetDate.toLocalDate().getDayOfWeek().getValue();
            for(int day = (dayOfWeek - 1); day >= 0; day --)
            {
                Date dateWeekly = Date.valueOf(targetDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate()
                    .minusDays(Long.valueOf(day)));
                PhoneUsage usageWeekly = phoneUsageRepository.findByUserIdAndDate(userId, dateWeekly).orElse(null);
                timeUsedWeekly.add((usageWeekly == null) ? 0 : usageWeekly.getTimeUsed());
            }


            PhoneUsageComparisonWeeklyResponse response = new PhoneUsageComparisonWeeklyResponse(userId, targetDate, timeUsedWeekly);
            log.debug(String.format("Answered call `GET /api/phoneUsage/%d/weekly` at date %s successfully with %s", userId, targetDateString, response.toString()));

            return ResponseEntity.status(HttpStatus.OK)
                .body(Response.setSuccess("주간 사용 시간 조회 성공!", "PhoneUsage/getAnalysisWeekly: Successful", response));
        }
        catch(Exception e)
        {
            e.printStackTrace();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.setFailure("서버 내부에 오류가 생겼어요!", "PhoneUsage/getAnalysisDaily: Internal Server Error"));
        }
    }
}
