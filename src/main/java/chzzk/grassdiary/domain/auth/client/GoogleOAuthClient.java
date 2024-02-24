package chzzk.grassdiary.domain.auth.client;

import chzzk.grassdiary.domain.auth.config.GoogleOAuthProperties;
import chzzk.grassdiary.domain.auth.service.dto.GoogleOAuthToken;
import chzzk.grassdiary.domain.auth.service.dto.GoogleUserInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 스프링 서버가 구글 서버에 인가 코드를 전달하여 액세스 토큰을 받는다.
 */
@Component
public class GoogleOAuthClient implements OAuthClient {
    private static final String OAUTH_GRANT_TYPE = "authorization_code";
    private final GoogleOAuthProperties googleOAuthProperties;
    private final RestTemplate restTemplate;

    public GoogleOAuthClient(GoogleOAuthProperties googleOAuthProperties, RestTemplateBuilder restTemplateBuilder) {
        this.googleOAuthProperties = googleOAuthProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * @param code     인가 코드
     * @param isSignUp 회원가입 여부
     * @return 구글 서버에서 발급한 액세스 토큰 (= 사용자 정보를 요청하기 위한 액세스 토큰)
     */
    @Override
    public GoogleOAuthToken getGoogleAccessToken(String code, boolean isSignUp) {
        // header 생성
        HttpHeaders httpHeaders = createGoogleAccessRequestHeaders();
        // body 생성
        MultiValueMap<String, String> httpBody = createBody(code, isSignUp);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(httpBody, httpHeaders);
        ResponseEntity<GoogleOAuthToken> googleOAuthTokenResponseEntity = restTemplate.postForEntity(
                googleOAuthProperties.getTokenUri(), request, GoogleOAuthToken.class);
        return googleOAuthTokenResponseEntity.getBody();
    }

    /**
     * @param accessToken 구글 서버로부터 발급받은 액세스 토큰
     * @return 구글 서버로부터 받은 사용자 정보
     */
    @Override
    public GoogleUserInfo getGoogleUserInfo(String accessToken) {
        // 스프링 서버는 HTTP Header에 Authorization으로 Access Token을 담아서 구글 서버에 사용자 정보를 요청한다.
        // 헤더 생성
        HttpHeaders httpHeaders = createUserInfoRequestHeaders(accessToken);

        // GET /userInfo/v2/me Authorization: Bearer access_token 요청
        return restTemplate.exchange(
                        googleOAuthProperties.getTokenUri(),
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders),
                        GoogleUserInfo.class)
                .getBody();
    }

    private HttpHeaders createGoogleAccessRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

    private MultiValueMap<String, String> createBody(String code, boolean isSignUp) {
        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("code", code);
        httpBody.add("client_id", googleOAuthProperties.getClientId());
        httpBody.add("client_secret", googleOAuthProperties.getClientSecret());
        // 회원가입, 로그인에 대한 redirect_url이 다르므로 isSignUp으로 판단
        httpBody.add("redirect_uri", googleOAuthProperties.getRedirectUri());
        httpBody.add("grant_type", OAUTH_GRANT_TYPE);
        return httpBody;
    }

    private HttpHeaders createUserInfoRequestHeaders(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);
        return httpHeaders;
    }
}
