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

    @Query("SELECT d FROM Diary d WHERE d.isPrivate = false"
            + " AND d.createdAt >= :startOfWeek AND d.createdAt <= :endOfWeek"
            + " ORDER BY d.likeCount DESC, d.createdAt ASC")
    Page<Diary> findTop10DiariesThisWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek, PageRequest pageRequest);
    
    @Query("SELECT d FROM Diary d WHERE d.isPrivate = false"
            + " AND d.id < :cursorId"
            + " ORDER BY d.createdAt DESC, d.id DESC")
    List<Diary> findLatestDiaries(Long cursorId, Pageable pageable);
}

