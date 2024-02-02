package chzzk.grassdiary.domain.diary;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Diary {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ConditionLevel conditionLevel;
    @ManyToOne
    private Member member;

    @Column(columnDefinition = "TEXT")
    private String content;
    private Boolean isPrivate;
    private Boolean hasImage;
    private Integer likeCount;

}
