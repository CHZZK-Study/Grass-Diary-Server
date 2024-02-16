package chzzk.grassdiary.web.dto.member;

import chzzk.grassdiary.domain.diary.Diary;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GrassInfoDTO {
    List<GrassInfo> grassList;
    String colorRGB;

    public GrassInfoDTO(List<Diary> diaryHistory, String rgb) {
        grassList = diaryHistory.stream()
                .map(diary -> new GrassInfo(
                        diary.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        diary.getConditionLevel().getTransparency()))
                .collect(Collectors.toList());
        colorRGB = rgb;
    }
}
