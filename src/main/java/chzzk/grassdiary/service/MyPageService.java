package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.color.ColorCode;
import chzzk.grassdiary.domain.color.ColorCodeRepository;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.member.GrassInfoDTO;
import chzzk.grassdiary.web.dto.member.MemberInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {
    MemberRepository memberRepository;
    DiaryRepository diaryRepository;

    @Transactional(readOnly = true)
    public MemberInfoDTO findProfileById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + id + ")"));
        return new MemberInfoDTO(member.getProfileImageUrl(), member.getNickname(), member.getProfileIntro());
    }

    @Transactional(readOnly = true)
    public GrassInfoDTO findGrassHistoryById(Long memberId) {
        List<Diary> diaryHistory = diaryRepository.findDiaryByIdOrderByCreatedAtDesc(memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + memberId + ")"));
        ColorCode colorCode = member.getCurrentColorCode();
        return new GrassInfoDTO(diaryHistory, colorCode.getRgb());
    }
}
