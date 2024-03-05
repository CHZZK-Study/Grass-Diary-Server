package chzzk.grassdiary.service.diary;

import chzzk.grassdiary.domain.color.ColorCode;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryImageRepository;
import chzzk.grassdiary.domain.diary.DiaryLikeRepository;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.diary.tag.DiaryTag;
import chzzk.grassdiary.domain.diary.tag.DiaryTagRepository;
import chzzk.grassdiary.domain.diary.tag.MemberTags;
import chzzk.grassdiary.domain.diary.tag.MemberTagsRepository;
import chzzk.grassdiary.domain.diary.tag.TagList;
import chzzk.grassdiary.domain.diary.tag.TagListRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.diary.CountAndMonthGrassDTO;
import chzzk.grassdiary.web.dto.diary.DiaryDTO;
import chzzk.grassdiary.web.dto.diary.DiarySaveDTO;
import chzzk.grassdiary.web.dto.diary.DiaryUpdateDTO;
import chzzk.grassdiary.web.dto.diary.PopularDiaryDTO;
import chzzk.grassdiary.web.dto.member.GrassInfoDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryLikeRepository diaryLikeRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final TagListRepository tagListRepository;
    private final MemberTagsRepository memberTagsRepository;
    private final MemberRepository memberRepository;
    private final DiaryTagRepository diaryTagRepository;

    @Transactional
    public Long save(Long id, DiarySaveDTO.Request requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id = " + id));

        Diary diary = diaryRepository.save(requestDto.toEntity(member));

        if (requestDto.getHashtags() != null) {
            for (String hashtag : requestDto.getHashtags()) {
                TagList tagList = tagListRepository.findByTag(hashtag)
                        .orElseGet(() -> tagListRepository.save(new TagList(hashtag)));
                MemberTags memberTags = memberTagsRepository.findByMemberIdAndTagList(member.getId(), tagList)
                        .orElseGet(() -> memberTagsRepository.save(new MemberTags(member, tagList)));
                diaryTagRepository.save(new DiaryTag(diary, memberTags));
            }
        }

        return diary.getId();
    }

    @Transactional
    public Long update(Long id, DiaryUpdateDTO.Request requestDto) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));

        diary.update(requestDto.getContent(), requestDto.getIsPrivate(), requestDto.getHasImage(),
                requestDto.getHasTag(), requestDto.getConditionLevel());

        //update가 일어날 때 tag_list 테이블은 놔둬야하나?
        // 다른 곳에서도 해당 태그를 참조할 수 있는데..
        // 하지만 그냥 놔두면 계속 누적 됨. -> 나중에 한번에 삭제?
        // TagList 테이블에 태그 사용횟수를 기록하는 column을 추가해서 그 값이 0 이면 삭제하도록하는건 어떨까?
        if (requestDto.getHashtags() != null) {
            // tagList는 검색 후 추가하도록 하고
            // 나머지는 위의 diary 처럼 update를 만들어주자
            // update를 해야되나? 모두 삭제하고 새로 추가해줘야되나?
            for (String hashtag : requestDto.getHashtags()) {
                TagList tagList = tagListRepository.findByTag(hashtag)
                        .orElseGet(() -> tagListRepository.save(new TagList(hashtag)));
                MemberTags memberTags = memberTagsRepository.findByMemberIdAndTagList(diary.getMember().getId(),
                                tagList)
                        .orElseGet(() -> memberTagsRepository.save(new MemberTags(diary.getMember(), tagList)));
                diaryTagRepository.save(new DiaryTag(diary, memberTags));
            }
        }

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));

        diaryRepository.delete(diary);
    }

    @Transactional(readOnly = true)
    public DiaryDTO findById(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));
        //조회한 결과를 담은 DTO 객체를 생성해서 반환
//        List<DiaryTag> diaryTags = diaryTagRepository.findAllByDiaryId(diary.getId());
//        List<TagList> tags = new ArrayList<>();
//        for (DiaryTag diaryTag : diaryTags) {
//            tags.add(diaryTag.getMemberTags().getTagList());
//        }

        List<MemberTags> diaryTags = diaryTagRepository.findMemberTagsByDiaryId(diary.getId());
        List<TagList> tags = diaryTags.stream()
                .map(MemberTags::getTagList)
                .toList();

//        return new DiaryResponseDTO(diary, tags);
        return DiaryDTO.from(diary, tags);
    }

    @Transactional(readOnly = true)
    public Page<DiaryDTO> findAll(Pageable pageable, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + memberId + ")"));

        return diaryRepository.findDiaryByMemberId(memberId, pageable)
                .map(diary -> {
                    List<MemberTags> diaryTags = diaryTagRepository.findMemberTagsByDiaryId(diary.getId());
                    List<TagList> tags = diaryTags.stream()
                            .map(MemberTags::getTagList)
                            .toList();
                    return DiaryDTO.from(diary, tags);
                });
    }

    @Transactional(readOnly = true)
    public DiaryDTO findByDate(Long id, String date) {

        LocalDate localDate = LocalDate.parse(date);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);

        List<Diary> diaryList = diaryRepository.findByMemberIdAndCreatedAtBetween(id, startOfDay, endOfDay);
        if (diaryList.size() != 1) {
            // TODO: 일기를 찾지 못한 경우 보내는 값 설정 해야함
            return null;
        }

        Diary diary = diaryList.get(0);
        System.out.println(diary.getContent());

        // 해시태그 리스트 가져오기
        List<MemberTags> diaryTags = diaryTagRepository.findMemberTagsByDiaryId(diary.getId());
        List<TagList> tags = diaryTags.stream()
                .map(MemberTags::getTagList)
                .toList();

        return new DiaryDTO(
                diary.getId(),
                diary.getContent(),
                tags,
                diary.getConditionLevel().getTransparency(),
                diary.getIsPrivate(),
                diary.getDiaryLikes().size(),
                diary.getCreatedAt().format(DateTimeFormatter.ofPattern("yy년 MM월 dd일")),
                diary.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    /**
     * 유저의 총 잔디 개수(count) 유저의 이번 달 잔디 정보(grassInfoDTO<grassList>, colorRGB>)
     */
    @Transactional(readOnly = true)
    public CountAndMonthGrassDTO countAllAndMonthGrass(Long memberId) {
        List<Diary> allByMemberId = diaryRepository.findAllByMemberId(memberId);

        LocalDate startOfMonth = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth());
        LocalDateTime startOfDay = startOfMonth.atStartOfDay();
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + memberId + ")"));

        List<Diary> thisMonthHistory = diaryRepository.findByMemberIdAndCreatedAtBetween(memberId, startOfDay,
                endOfToday);
        ColorCode colorCode = member.getCurrentColorCode();

        return new CountAndMonthGrassDTO(
                allByMemberId.size(),
                new GrassInfoDTO(thisMonthHistory, colorCode.getRgb())
        );
    }

    /**
     * 대표 일기(오늘의 좋아요 가장 많은 받은 일기 10개)
     */
    @Transactional(readOnly = true)
    public List<PopularDiaryDTO> popularDiary(Long memberId) {
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth());

        List<Diary> popularDiaries = diaryRepository
                .findTop10ByIsPrivateFalseAndCreatedAtBetweenOrderByDiaryLikesDesc(today.atStartOfDay(),
                        today.atTime(LocalTime.MAX));

        if (popularDiaries.isEmpty()) {
            // TODO: 오늘의 일기 없음
            return null;
        }

        return popularDiaries.stream()
                .map(diary -> new PopularDiaryDTO(
                        diary.getId(),
                        diary.getContent(),
                        diary.getDiaryLikes().stream()
                                .anyMatch(diaryLike -> diaryLike.getMember().getId().equals(memberId))
                )).collect(Collectors.toList());
    }
}
