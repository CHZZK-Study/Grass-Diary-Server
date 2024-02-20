package chzzk.grassdiary.domain.auth.client;

import chzzk.grassdiary.domain.auth.service.dto.GoogleOAuthToken;

public interface OAuthClient {
    GoogleOAuthToken getGoogleAccessToken(String code, boolean isSignUp);
}
