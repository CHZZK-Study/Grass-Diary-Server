package chzzk.grassdiary.web.dto.share;

public record Top10DiariesDto(
        Long diaryId,
        String diaryContent,
        int diaryLikeCount,
        String nickname) {
}
