package chzzk.grassdiary.web.dto.member;

import chzzk.grassdiary.domain.member.Member;

public record MemberIdDto(
        Long memberId
) {
    public static MemberIdDto from(Member foundMember) {
        return new MemberIdDto(foundMember.getId());
    }
}
