package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.diary.tag.TagList;

import java.util.List;

public record DiaryDTO (
        Long diaryId,
        String content,
        List<TagList> tags,
        Float transparency,
        Boolean isPrivate,
        Integer likeCount,
        String createdDate,
        String createdAt
) {
}
