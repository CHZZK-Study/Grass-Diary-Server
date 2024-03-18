package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.member.Member;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiarySaveRequestDTO {
    private Member member;
    private String content;
    private Boolean isPrivate;
    private Boolean hasImage;
    private Boolean hasTag;
    private ConditionLevel conditionLevel;
    private List<String> hashtags;

    // DTO -> Entity
    public Diary toEntity(Member member) {
        return Diary.builder()
                .member(member)
                .content(content)
                .isPrivate(isPrivate)
                .hasImage(hasImage)
                .hasTag(hasTag)
                .conditionLevel(conditionLevel)
                .build();
    }
}
