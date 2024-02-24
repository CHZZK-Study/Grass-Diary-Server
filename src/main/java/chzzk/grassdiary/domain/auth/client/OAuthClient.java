package chzzk.grassdiary.domain.auth.client;

import chzzk.grassdiary.domain.auth.service.dto.GoogleOAuthToken;
import chzzk.grassdiary.domain.auth.service.dto.GoogleUserInfo;

public interface OAuthClient {
    GoogleOAuthToken getGoogleAccessToken(String code, boolean isSignUp);

    GoogleUserInfo getGoogleUserInfo(String accessToken);
}
