package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryLike;
import chzzk.grassdiary.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryLikeDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private Member member;
        private Diary diary;

        // DTO -> Entity
        public DiaryLike toEntity() {
            DiaryLike diaryLike = DiaryLike.builder()
                    .id(id)
                    .member(member)
                    .diary(diary)
                    .build();

            return diaryLike;
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private Member member;
        private Diary diary;

        // Entity -> DTO
        public Response(DiaryLike diaryLike) {
            this.id = diaryLike.getId();
            this.member = diaryLike.getMember();
            this.diary = diaryLike.getDiary();
        }
    }
}
