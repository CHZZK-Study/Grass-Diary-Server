package chzzk.grassdiary.domain.member.entity;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter // DBTest에서 임시 사용
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, name = "given_name", unique = true)
    @Size(min = 1, max = 20)
    private String nickname;  // auth

    @Column(nullable = false, length = 200)
    @Email(message = "유효하지 않은 이메일 주소입니다.")
    private String email; // auth

    @Lob
    private String picture; // auth

    private Long grassCount;

    private Integer rewardPoint;

    @ColumnDefault("false")
    private Boolean hasNewColor;

    @ManyToOne(fetch = FetchType.LAZY)
    private ColorCode currentColorCode;

    private String profileIntro;
}