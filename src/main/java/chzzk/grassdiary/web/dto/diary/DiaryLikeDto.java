package chzzk.grassdiary.web.dto.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryLike;
import chzzk.grassdiary.domain.member.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiaryLikeDto {

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
