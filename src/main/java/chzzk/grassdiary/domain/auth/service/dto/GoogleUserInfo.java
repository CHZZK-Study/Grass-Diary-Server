package chzzk.grassdiary.domain.auth.service.dto;

import chzzk.grassdiary.domain.member.Member;

/**
 * 구글 서버로부터 받은 사용자 정보
 */
public record GoogleUserInfo(
        String email,
        String displayName,
        String imageUrl
) {
    public Member toMember() {
        return Member.builder()
                .email(email)
                .nickname(displayName)
                .profileImageUrl(imageUrl)
                .build();
    }
}
