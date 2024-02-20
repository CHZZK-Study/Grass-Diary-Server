package chzzk.grassdiary.domain.auth.util;

import chzzk.grassdiary.domain.auth.config.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthUriGenerator implements OAuthUriGenerator {

    private final GoogleOAuthProperties googleOAuthProperties;

    /**
     * @return Google 인증서버에 대한 인가코드 요청 URI
     */
    @Override
    public String generateSignUpUrl() {
        return String.format(
                "%s?client_id=%s&redirect_uri=%s&response_type=%s&scope=%s",
                googleOAuthProperties.getAuthorizationUri(),
                googleOAuthProperties.getClientId(),
                googleOAuthProperties.getSignUpRedirectUri(),
                googleOAuthProperties.getResponseType(),
                googleOAuthProperties.getScope()
        );
    }

    @Override
    public String generateSignInUrl() {
        return String.format(
                "%s?client_id=%s&redirect_uri=%s&response_type=%s&scope=%s",
                googleOAuthProperties.getAuthorizationUri(),
                googleOAuthProperties.getClientId(),
                googleOAuthProperties.getSignInRedirectUri(),
                googleOAuthProperties.getResponseType(),
                googleOAuthProperties.getScope()
        );
    }
}