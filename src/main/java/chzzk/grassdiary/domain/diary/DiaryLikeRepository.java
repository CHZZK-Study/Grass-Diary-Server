package chzzk.grassdiary.domain.diary;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Long> {
    Optional<DiaryLike> findByDiaryIdAndMemberId(Long diaryId, Long memberId);

}
