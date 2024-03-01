package chzzk.grassdiary.auth.client;

import chzzk.grassdiary.auth.service.dto.GoogleOAuthToken;
import chzzk.grassdiary.auth.service.dto.GoogleUserInfo;

public interface OAuthClient {
    GoogleOAuthToken getGoogleAccessToken(String code);

    GoogleUserInfo getGoogleUserInfo(String accessToken);
}
