package chzzk.grassdiary.domain.auth.controller;

import chzzk.grassdiary.domain.auth.service.OAuthSignUpService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthSignUpController {
    private final OAuthSignUpService oAuthSignUpService;

    @GetMapping("/api/signup/auth/google")
    public void signUpGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = oAuthSignUpService.findSignUpRedirectUri();
        response.sendRedirect(redirectUri);
    }

    // todo
    @GetMapping("/signup/auth/code/google")
    public void signUpAuthorizeUser(@RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        response.getWriter().write("callback");
        //return ResponseEntity.ok("callback"); // test
    }
}
