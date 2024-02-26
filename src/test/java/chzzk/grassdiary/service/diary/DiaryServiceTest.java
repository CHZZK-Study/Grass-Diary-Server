package chzzk.grassdiary.service.diary;

import chzzk.grassdiary.domain.color.ColorCode;
import chzzk.grassdiary.domain.color.ColorCodeRepository;
import chzzk.grassdiary.domain.color.ConditionLevel;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.diary.DiaryDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ColorCodeRepository colorCodeRepository;

    @Test
    public void 글_생성() {
        String expectedContent = "diary content";
        String expectedNickname = "testNickName";

        ColorCode colorCode = ColorCode.builder()
                .colorName("GREEN")
                .rgb("000,000,000")
                .build();
        Member member = Member.builder()
                .id(1L)
                .nickname(expectedNickname)
                .email("testEmail@test.com")
                .currentColorCode(colorCode)
                .build();
        DiaryDto.Request requestDto = DiaryDto.Request.builder()
                .id(1L)
                .member(member)
                .content(expectedContent)
                .isPrivate(true)
                .hasImage(false)
                .conditionLevel(ConditionLevel.LEVEL_10)
                .build();

        colorCodeRepository.save(colorCode);
        memberRepository.save(member);
        diaryService.save(requestDto);

        // 글 작성자 확인
        Assertions.assertThat(diaryRepository.findById(1L).get().getMember().getNickname()).isEqualTo(expectedNickname);
        // 글 내용 확인
        Assertions.assertThat(diaryRepository.findById(1L).get().getContent()).isEqualTo(expectedContent);
    }

}
