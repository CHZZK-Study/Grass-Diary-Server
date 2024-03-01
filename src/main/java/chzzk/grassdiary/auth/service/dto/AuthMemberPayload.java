package chzzk.grassdiary.auth.service.dto;

import chzzk.grassdiary.domain.member.Member;

public record AuthMemberPayload(Long id) {
    public static AuthMemberPayload from(Member member) {
        return new AuthMemberPayload(member.getId());
    }

    public static AuthMemberPayload from(Long memberId) {
        return new AuthMemberPayload(memberId);
    }
}
