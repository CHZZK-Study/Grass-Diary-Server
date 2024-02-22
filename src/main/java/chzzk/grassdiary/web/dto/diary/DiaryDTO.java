package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.diary.tag.MemberTags;

import java.util.List;

public record DiaryDTO (
        Long diaryId,
        String content,
        List<MemberTags> tags,
        Float transparency,
        Boolean isPrivate,
        Integer likeCount,
        String createdDate,
        String createdAt
) {
}
