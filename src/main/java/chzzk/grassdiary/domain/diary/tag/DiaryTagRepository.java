package chzzk.grassdiary.domain.diary.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {
    List<DiaryTag> findByDiaryId(Long diaryId);
}
