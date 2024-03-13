package chzzk.grassdiary.web.dto.share;

import chzzk.grassdiary.domain.diary.Diary;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record LatestDiariesDto(
        Long diaryId,
        String content,
        int diaryLikeCount,
        String nickname,
        String createdAt) {

    public static List<LatestDiariesDto> of(List<Diary> diaries) {
        return diaries.stream()
                .map(d -> new LatestDiariesDto(
                        d.getId(),
                        d.getContent(),
                        d.getDiaryLikes().size(),
                        d.getMember().getNickname(),
                        d.getCreatedAt()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )).toList();
    }
}
