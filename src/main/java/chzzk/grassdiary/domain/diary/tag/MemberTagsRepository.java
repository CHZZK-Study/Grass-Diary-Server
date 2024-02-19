package chzzk.grassdiary.domain.diary.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberTagsRepository extends JpaRepository<MemberTags, Long> {
    @Query("SELECT mt.tagList.id FROM MemberTags mt WHERE mt.member.id = :memberId")
    List<Long> findTagIdsByMemberId(Long memberId);
}
