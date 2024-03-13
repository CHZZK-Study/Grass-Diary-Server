package chzzk.grassdiary.domain.diary;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Page<Diary> findDiaryByMemberId(Long memberId, Pageable pageable);

    List<Diary> findAllByMemberIdOrderByCreatedAt(Long memberId);

    @Query("SELECT d FROM Diary d WHERE d.member.id = :memberId AND " +
            "d.createdAt >= :startOfDay AND d.createdAt <= :endOfDay")
    List<Diary> findByMemberIdAndCreatedAtBetween(Long memberId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Diary> findAllByMemberId(Long memberId);

    @Query("SELECT d FROM Diary d LEFT JOIN d.diaryLikes dl WHERE d.isPrivate = false AND d.createdAt >= :startOfDay AND d.createdAt <= :endOfDay"
            + " GROUP BY d.id ORDER BY COUNT(dl.id) DESC, d.createdAt ASC")
    Page<Diary> findByIsPrivateFalseAndCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay,
                                                        PageRequest pageRequest);

    List<Diary> findByIsPrivateFalseAndIdLessThanOrderByCreatedAtDesc(Long cursorId, Pageable pageable);
}

