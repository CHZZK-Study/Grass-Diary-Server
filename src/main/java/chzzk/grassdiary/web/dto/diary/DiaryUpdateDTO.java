package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryUpdateDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Member member;
        private String content;
        private Boolean isPrivate;
        private Boolean hasImage;
        private Boolean hasTag;
        private ConditionLevel conditionLevel;

        // DTO -> Entity
        public Diary toEntity() {
            Diary diary = Diary.builder()
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
        private final Long id;
        private final Long memberId;
        private final String content;
        private final Boolean isPrivate;
        //        private List<DiaryLikeDto.Response> diaryLikes = new ArrayList<>(); //dto로 변경
        private final Boolean hasImage;
        private final Boolean hasTag;
        private final ConditionLevel conditionLevel;

        // Entity -> DTO
        public Response(Diary diary) {
            this.id = diary.getId();
            this.memberId = diary.getMember().getId();
            this.content = diary.getContent();
            this.isPrivate = diary.getIsPrivate();
//            this.diaryLikes = diary.getDiaryLikes().stream().map(DiaryLikeDto.Response::new)
//                    .collect(Collectors.toList());
            this.hasImage = diary.getHasImage();
            this.hasTag = diary.getHasTag();
            this.conditionLevel = diary.getConditionLevel();
        }
    }
}
