package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.diary.tag.DiaryTag;
import chzzk.grassdiary.domain.diary.tag.DiaryTagRepository;
import chzzk.grassdiary.domain.diary.tag.MemberTags;
import chzzk.grassdiary.domain.diary.tag.MemberTagsRepository;
import chzzk.grassdiary.domain.diary.tag.TagList;
import chzzk.grassdiary.domain.diary.tag.TagListRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.diary.CountDTO;
import chzzk.grassdiary.web.dto.diary.DiaryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiaryServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    MemberTagsRepository memberTagsRepository;
    @Autowired
    TagListRepository tagListRepository;
    @Autowired
    DiaryTagRepository diaryTagRepository;

    @Autowired
    DiaryService diaryService;

    protected List<Member> members;
    protected List<Diary> diaries;
    protected List<DiaryTag> diaryTags;
    protected List<MemberTags> memberTags;
    protected List<TagList> tagLists;

    @BeforeEach
    void beforeEach() {
        // 멤버 3명 만들기
        members = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            members.add(Member.builder()
                    .nickname("testMember" + i)
                    .email("member" + i + "@test.com")
                    .build());
        }
        members = memberRepository.saveAll(members);

        // 일기 추가
        diaries = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            diaries.add(Diary.builder()
                    .member(members.get(i%3))
                    .content("testContent" + i)
                    .hasTag(false)
                    .conditionLevel(ConditionLevel.LEVEL_5)
                    .build());
        }
        diaries = diaryRepository.saveAll(diaries);
    }

    /**
     * 유저의 날짜별 일기 검색(잔디 클릭시 실행)
     * Web에서 DB에 값 집어넣고 테스트 해보면 작동하나 테스트 코드는 실패함. 테스트 코드 수정이 필요함.
     */
    @Test
    void findByDate() {
        // Given
        Long memberId = members.get(0).getId();

        System.out.println("memberId:" + memberId + ", today: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        // When
        DiaryDTO diaryDTO = diaryService.findByDate(memberId, LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        System.out.println("다이어리 사이즈: "+diaries.size());
        System.out.println("1번 다이어리: " + diaries.get(0).getContent());
        // Then
        assertThat(diaryDTO).isNotNull();
        assertThat(diaryDTO.content()).isEqualTo("testContent0");
        assertThat(diaryDTO.createdDate()).isEqualTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yy년 MM월 dd일")));

    }

    @Test
    void countAll() {
        // Given
        Long memberId = members.get(0).getId();

        // When
        CountDTO countDTO = diaryService.countAll(memberId);

        // Then
        assertThat(countDTO).isNotNull();
        assertThat(countDTO.count()).isEqualTo(1);
    }
}