package chzzk.grassdiary.domain.color;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // DBTest에서 임시 사용
@NoArgsConstructor
@Entity
public class ColorCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colorCode_id")
    private Long id;

    private String colorName;

    private String rgb;
}
