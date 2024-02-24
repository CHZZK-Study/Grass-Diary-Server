package chzzk.grassdiary.domain.auth.controller;

import chzzk.grassdiary.domain.auth.service.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @GetMapping("/google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = oAuthService.findRedirectUri();
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/code/google")
    public ResponseEntity<String> authorizeUser(
            @RequestParam("code") String code,
            HttpServletResponse response) {

        return ResponseEntity.ok("callback!!!");
    }
}
