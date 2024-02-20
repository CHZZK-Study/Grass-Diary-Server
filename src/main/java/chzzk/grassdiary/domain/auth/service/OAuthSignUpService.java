package chzzk.grassdiary.domain.auth.service;

import chzzk.grassdiary.domain.auth.util.GoogleOAuthUriGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthSignUpService {

    private final GoogleOAuthUriGenerator googleOAuthUriGenerator;

    /**
     * 사용자가 로그인 완료 및 권한 승인을 하면, 인증 서버는 클라이언트가 지정한 redirect_uri로 사용자를 리디렉트한다
     * <p>
     */
    public String findSignUpRedirectUri() {
        return googleOAuthUriGenerator.generateSignUpUrl();
    }
}
