package chzzk.grassdiary.auth.controller;

import chzzk.grassdiary.auth.service.OAuthService;
import chzzk.grassdiary.auth.service.dto.JWTTokenResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OAuth 컨트롤러", description = "소셜 로그인 API입니다.")
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @Value("${client.login-success-redirect-uri}")
    private String loginSuccessUri;

    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "구글 서버의 로그인 페이지로 redirect")
    })
    @GetMapping("/google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = oAuthService.findRedirectUri();
        response.sendRedirect(redirectUri);
    }

    @ApiResponses(
            @ApiResponse(responseCode = "302", description = "Google 소셜 로그인이 완료된 후, Access Token이 담긴 메인 페이지 URL로 리다이렉트합니다.",
                    content = @Content(
                            schema = @Schema(type = "string", example = "http://localhost:3000/main?accessToken=yourAccessToken")
                    ))
    )
    @GetMapping("/code/google")
    public void authorizeUser(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        JWTTokenResponse jwtToken = oAuthService.loginGoogle(code);
        String redirectUriWithJwt = generateRedirectUri(jwtToken.accessToken());
        log.info("jwt 토큰이 담긴 url = {}", redirectUriWithJwt);
        response.sendRedirect(redirectUriWithJwt);
    }

    private String generateRedirectUri(String accessToken) {
        return String.format("%s?accessToken=%s", loginSuccessUri, accessToken);
    }
}
