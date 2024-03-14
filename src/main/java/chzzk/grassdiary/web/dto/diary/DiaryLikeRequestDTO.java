package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryLike;
import chzzk.grassdiary.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryLikeRequestDTO {
    private Member member;
    private Diary diary;

    public DiaryLike toEntity(Diary diary, Member member) {
        DiaryLike diaryLike = DiaryLike.builder()
                .member(member)
                .diary(diary)
                .build();

        return diaryLike;
    }
}
