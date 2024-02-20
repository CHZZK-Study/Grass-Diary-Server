package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.member.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiaryDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private Member member;
        private String content;
        private Boolean isPrivate;
        private Boolean hasImage;
        private Boolean hasTag;
        private ConditionLevel conditionLevel;

        // DTO -> Entity
        public Diary toEntity() {
            Diary diary = Diary.builder()
                    .id(id)
                    .member(member)
                    .content(content)
                    .isPrivate(isPrivate)
                    .hasImage(hasImage)
                    .hasTag(hasTag)
                    .conditionLevel(conditionLevel)
                    .build();

            return diary;
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private Member member;
        private String content;
        private Boolean isPrivate;
        private List<DiaryLikeDto.Response> diaryLikes = new ArrayList<>(); //dto로 변경
        private Boolean hasImage;
        private Boolean hasTag;
        private ConditionLevel conditionLevel;

        // Entity -> DTO
        public Response(Diary diary) {
            this.id = diary.getId();
            this.member = diary.getMember();
            this.content = diary.getContent();
            this.isPrivate = diary.getIsPrivate();
            this.diaryLikes = diary.getDiaryLikes().stream().map(DiaryLikeDto.Response::new).collect(Collectors.toList());
            this.hasImage = diary.getHasImage();
            this.hasTag = diary.getHasTag();
            this.conditionLevel = diary.getConditionLevel();
        }
    }
}
