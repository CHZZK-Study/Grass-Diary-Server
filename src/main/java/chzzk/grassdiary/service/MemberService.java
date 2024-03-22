package chzzk.grassdiary.service;

import chzzk.grassdiary.auth.service.dto.GoogleUserInfo;
import chzzk.grassdiary.domain.color.ColorCode;
import chzzk.grassdiary.domain.color.ColorCodeRepository;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private static final String DEFAULT_COLOR_NAME = "GREEN";
    private static final String DEFAULT_RGB = "0,255,0";
    private final MemberRepository memberRepository;
    private final ColorCodeRepository colorCodeRepository;

    public Member createMemberIfNotExist(GoogleUserInfo googleUserInfo) {
        Optional<Member> foundMember = memberRepository.findByEmail(googleUserInfo.email());

        if (foundMember.isPresent()) {
            return foundMember.get();
        }

        Member member = Member.of(
                googleUserInfo.nickname(),
                googleUserInfo.email(),
                googleUserInfo.picture(),
                getDefaultColorCode());

        return memberRepository.save(member);
    }

    private ColorCode getDefaultColorCode() {
        return colorCodeRepository.findByColorName(DEFAULT_COLOR_NAME)
                .orElseGet(() -> colorCodeRepository.save(
                        ColorCode.builder()
                                .colorName(DEFAULT_COLOR_NAME)
                                .rgb(DEFAULT_RGB)
                                .build()
                ));
    }
}
