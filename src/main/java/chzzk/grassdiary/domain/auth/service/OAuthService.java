package chzzk.grassdiary.domain.auth.service;

import chzzk.grassdiary.domain.auth.client.GoogleOAuthClient;
import chzzk.grassdiary.domain.auth.jwt.JwtTokenProvider;
import chzzk.grassdiary.domain.auth.service.dto.GoogleOAuthToken;
import chzzk.grassdiary.domain.auth.service.dto.GoogleUserInfo;
import chzzk.grassdiary.domain.auth.service.dto.JWTTokenResponse;
import chzzk.grassdiary.domain.auth.util.GoogleOAuthUriGenerator;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OAuthService {
    private final GoogleOAuthUriGenerator googleOAuthUriGenerator;
    private final GoogleOAuthClient googleOAuthClient;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String findRedirectUri() {
        return googleOAuthUriGenerator.generateUrl();
    }

    public JWTTokenResponse signUpGoogle(String code) {
        GoogleOAuthToken googleAccessToken = googleOAuthClient.getGoogleAccessToken(code);

        GoogleUserInfo googleUserInfo = googleOAuthClient.getGoogleUserInfo(googleAccessToken.accessToken());

        Member member = createMemberIfNotExist(googleUserInfo);

        String accessToken = jwtTokenProvider.generateAccessTokenById(googleUserInfo.id());
        log.info("토큰 생성 완료 - 구글로부터 받은 사용자 id: {}", googleUserInfo.id());
        log.info("토큰 생성 완료 - repository에 저장된 사용자 id: {}", member.getEmail());

        return new JWTTokenResponse(accessToken);
    }

    private Member createMemberIfNotExist(GoogleUserInfo googleUserInfo) {
        Optional<Member> foundMember = memberRepository.findByEmail(googleUserInfo.email());

        if (foundMember.isPresent()) {
            return foundMember.get();
        }

        Member member = Member.builder()
                .nickname(googleUserInfo.nickname())
                .email(googleUserInfo.email())
                .picture(googleUserInfo.picture())
                .build();

        return memberRepository.save(member);
    }
}