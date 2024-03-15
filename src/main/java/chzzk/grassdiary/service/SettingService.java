package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.member.MemberUpdateRequest;
import chzzk.grassdiary.web.dto.member.MemberUpdatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SettingService {
    private final MemberRepository memberRepository;

    public MemberUpdatedResponse updateMemberInfo(Long id, MemberUpdateRequest request) {
        Member foundMember = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        foundMember.updateProfile(request.nickname(), request.profileIntro());

        memberRepository.save(foundMember);

        return new MemberUpdatedResponse(foundMember.getNickname(), foundMember.getProfileIntro());
    }
}
