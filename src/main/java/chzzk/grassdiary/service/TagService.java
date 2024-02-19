package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.diary.tag.MemberTagsRepository;
import chzzk.grassdiary.domain.diary.tag.TagListRepository;
import chzzk.grassdiary.web.dto.diary.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final MemberTagsRepository memberTagsRepository;
    private final TagListRepository tagListRepository;

    /**
     * 유저의 해시태그 리스트 반환
     */
    public List<TagDTO> getMemberTags(Long memberId) {
        // 멤버가 사용한 태그의 tag_id 목록 가져오기
        List<Long> tagIds = memberTagsRepository.findTagIdsByMemberId(memberId);

        // 태그 DTO 조회
        return tagListRepository.findTagDTOByIdIn(tagIds).stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getTag())).toList();
    }
}
