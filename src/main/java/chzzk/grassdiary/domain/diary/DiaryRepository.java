package chzzk.grassdiary.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findDiaryByIdOrderByCreatedAtDesc(Long memberId);
}
