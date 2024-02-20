package chzzk.grassdiary.domain.auth.controller;

import chzzk.grassdiary.domain.auth.service.OAuthSignInService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthSignInController {
    private final OAuthSignInService oAuthSignInService;

    @GetMapping("/api/signin/auth/google")
    public void signInGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = oAuthSignInService.findSignInRedirectUri();

        response.sendRedirect(redirectUri);
    }

    // todo
    @GetMapping("/signin/auth/code/google")
    public ResponseEntity<String> signInAuthorizeUser(@RequestParam("code") String code, HttpServletResponse response) {
        return ResponseEntity.ok("callback"); // test
    }
}
