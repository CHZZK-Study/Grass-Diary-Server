package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.color.ColorCodeRepository;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.member.MemberInfoDTO;
import chzzk.grassdiary.web.dto.mypage.GetProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPageService {
    MemberRepository memberRepository;
    DiaryRepository diaryRepository;
    ColorCodeRepository colorCodeRepository;

    @Transactional(readOnly = true)
    public MemberInfoDTO findProfileById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + id + ")"));
        return new MemberInfoDTO(member.getProfileImageUrl(), member.getNickname(), member.getProfileIntro());
    }
}
