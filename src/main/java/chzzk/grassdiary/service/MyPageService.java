package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.color.ColorCode;
import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.repository.MemberRepository;
import chzzk.grassdiary.web.dto.member.GrassInfoDTO;
import chzzk.grassdiary.web.dto.member.MemberInfoDTO;
import chzzk.grassdiary.web.dto.member.TotalRewardDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    @Transactional(readOnly = true)
    public MemberInfoDTO findProfileById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + id + ")"));
        return new MemberInfoDTO(member.getPicture(), member.getNickname(), member.getProfileIntro());
    }

    @Transactional(readOnly = true)
    public GrassInfoDTO findGrassHistoryById(Long memberId) {
        List<Diary> diaryHistory = diaryRepository.findAllByMemberIdOrderByCreatedAt(memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + memberId + ")"));
        ColorCode colorCode = member.getCurrentColorCode();
        return new GrassInfoDTO(diaryHistory, colorCode.getRgb());
    }

    /**
     * 사용자의 리워드 포인트 반환
     */
    public TotalRewardDTO findTotalRewardById(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버 입니다. (id: " + memberId + ")"));

        Integer rewardPoint = memberRepository.findRewardPointById(memberId);
        return new TotalRewardDTO(rewardPoint);
    }
}
