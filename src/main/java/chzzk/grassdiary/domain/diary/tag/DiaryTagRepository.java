package chzzk.grassdiary.domain.diary.tag;

import chzzk.grassdiary.domain.diary.Diary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {
    @Query("SELECT dt.diary FROM DiaryTag dt " +
            "WHERE dt.memberTags.member.id = :memberId AND dt.memberTags.tagList.id = :tagId " +
            "ORDER BY dt.diary.createdAt DESC")
    List<Diary> findByMemberIdAndTagId(Long memberId, Long tagId);

    @Query("SELECT dt.memberTags FROM DiaryTag dt WHERE dt.diary.id = :diaryId")
    List<MemberTags> findMemberTagsByDiaryId(Long diaryId);
}
