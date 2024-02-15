package chzzk.grassdiary.service;

import chzzk.grassdiary.web.dto.main.TodayInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class MainService {

    public TodayInfoDTO getTodayInfo() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("오늘은 M월 d일입니다.");
        String todayDate = today.format(dateTimeFormatter);

        String todayQuestion = "오늘의 질문";

        return new TodayInfoDTO(todayDate, todayQuestion);
    }
}
