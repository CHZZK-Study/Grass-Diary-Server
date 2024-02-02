package chzzk.grassdiary.domain.member;

import chzzk.grassdiary.domain.color.ColorCode;
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
public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String profileImage;
    private Long grassCount;
    private Integer rewardPoint;
    private Boolean hasNewColor;

    @ManyToOne
    private ColorCode colorCode;
    private String profileIntro;


}
