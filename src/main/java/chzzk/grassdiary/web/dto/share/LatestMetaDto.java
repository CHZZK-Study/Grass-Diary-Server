package chzzk.grassdiary.web.dto.share;

import chzzk.grassdiary.domain.diary.Diary;
import java.util.List;

public record LatestMetaDto(
        int count,
        boolean hasMore
) {
    public static LatestMetaDto of(List<Diary> diaries, boolean hasMore) {
        return new LatestMetaDto(
                diaries.size(),
                hasMore
        );
    }
}
