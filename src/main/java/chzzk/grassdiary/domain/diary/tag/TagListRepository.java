package chzzk.grassdiary.domain.diary.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagListRepository extends JpaRepository<TagList, Long> {
    List<TagList> findTagDTOByIdIn(List<Long> ids);
}
