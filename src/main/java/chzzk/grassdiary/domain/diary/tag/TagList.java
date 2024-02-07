package chzzk.grassdiary.domain.diary.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
}

