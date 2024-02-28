package chzzk.grassdiary.domain.diary.tag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberTagsRepository extends JpaRepository<MemberTags, Long> {
    @Query("SELECT mt.tagList.id FROM MemberTags mt WHERE mt.member.id = :memberId")
    List<Long> findTagIdsByMemberId(Long memberId);
}
