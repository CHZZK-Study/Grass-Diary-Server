package chzzk.grassdiary.domain.auth.util;


public interface OAuthUriGenerator {
    String generateSignUpUrl();

    String generateSignInUrl();
}
