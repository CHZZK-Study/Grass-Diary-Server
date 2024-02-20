package chzzk.grassdiary.domain.auth.service;

import chzzk.grassdiary.domain.auth.util.GoogleOAuthUriGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthSignInService {
    private final GoogleOAuthUriGenerator googleOAuthUriGenerator;

    public String findSignInRedirectUri() {
        return googleOAuthUriGenerator.generateSignInUrl();
    }
}
