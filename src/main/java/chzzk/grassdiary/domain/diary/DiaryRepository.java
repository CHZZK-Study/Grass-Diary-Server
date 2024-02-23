package chzzk.grassdiary.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findDiaryByIdOrderByCreatedAtDesc(Long memberId);

    @Query("SELECT d FROM Diary d WHERE d.member.id = :memberId AND " +
            "d.createdAt >= :startOfDay AND d.createdAt <= :endOfDay")
    List<Diary> findByMemberIdAndCreatedAtBetween(Long memberId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Diary> findAllByMemberId(Long memberId);

    List<Diary> findTop10ByIsPrivateFalseAndCreatedAtBetweenOrderByDiaryLikesDesc(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
