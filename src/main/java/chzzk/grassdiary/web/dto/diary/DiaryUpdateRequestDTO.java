package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryUpdateRequestDTO {
    private String content;
    private Boolean isPrivate;
    private Boolean hasImage;
    private Boolean hasTag;
    private ConditionLevel conditionLevel;
    private List<String> hashtags;
}
