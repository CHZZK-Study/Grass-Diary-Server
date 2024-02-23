package chzzk.grassdiary.domain.member;

import chzzk.grassdiary.domain.base.BaseTimeEntity;
import chzzk.grassdiary.domain.color.ColorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter // DBTest에서 임시 사용
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private String email;

    @Lob
    private String profileImageUrl;

    private Long grassCount;

    @ColumnDefault("0")
    private Integer rewardPoint;

    @ColumnDefault("false")
    private Boolean hasNewColor;

    @ManyToOne(fetch = FetchType.LAZY)
    private ColorCode currentColorCode;

    private String profileIntro;

    @Builder
    public Member(String nickname, String email, ColorCode currentColorCode) {
        this.nickname = nickname;
        this.email = email;
        this.currentColorCode = currentColorCode;
        this.rewardPoint = 0;
    }
}
