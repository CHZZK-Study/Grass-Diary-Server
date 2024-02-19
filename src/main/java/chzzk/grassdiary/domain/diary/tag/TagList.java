package chzzk.grassdiary.domain.diary.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter // DBTest에서 임시 사용
@NoArgsConstructor
@Entity
public class TagList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagList_id")
    private Long id;

    @Column(nullable = false)
    private String tag;

    @OneToMany(mappedBy = "tagList")
    private List<MemberTags> memberTags = new ArrayList<>();

    @Builder
    public TagList(String tag) {
        this.tag = tag;
    }
}

