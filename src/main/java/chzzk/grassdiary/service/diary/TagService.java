package chzzk.grassdiary.service.diary;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.tag.DiaryTagRepository;
import chzzk.grassdiary.domain.diary.tag.MemberTags;
import chzzk.grassdiary.domain.diary.tag.MemberTagsRepository;
import chzzk.grassdiary.domain.diary.tag.TagList;
import chzzk.grassdiary.domain.diary.tag.TagListRepository;
import chzzk.grassdiary.web.dto.diary.DiaryDTO;
import chzzk.grassdiary.web.dto.diary.TagDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {

    private final MemberTagsRepository memberTagsRepository;
    private final TagListRepository tagListRepository;
    private final DiaryTagRepository diaryTagRepository;

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

    /**
     * 유저의 다이어리 태그로 다이어리 검색
     */
    public List<DiaryDTO> findByHashTagId(Long memberId, Long tagId) {
        List<Diary> diaries = diaryTagRepository.findByMemberIdAndTagId(memberId, tagId);

        return diaries.stream()
                .map(diary -> {
                    List<MemberTags> diaryTags = diaryTagRepository.findMemberTagsByDiaryId(diary.getId());
                    List<TagList> tags = diaryTags.stream()
                            .map(MemberTags::getTagList)
                            .toList();
                    return DiaryDTO.from(diary, tags);
                })
                .collect(Collectors.toList());
    }
}
