package chzzk.grassdiary.auth.config;

import chzzk.grassdiary.auth.exception.AuthenticationException;
import chzzk.grassdiary.auth.jwt.JwtTokenExtractor;
import chzzk.grassdiary.auth.jwt.JwtTokenProvider;
import chzzk.grassdiary.domain.member.Member;
import chzzk.grassdiary.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String accessToken = jwtTokenExtractor.extractAccessToken(request);
        Long id = jwtTokenProvider.extractIdFromAccessToken(accessToken);
        validateMemberExist(id);
        return true;
    }

    private void validateMemberExist(Long id) {
        Optional<Member> foundMember = memberRepository.findById(id);
        if (foundMember.isEmpty()) {
            String logMessage = "인증 실패(미인증 사용자 요청) - member_id: " + id;
            throw new AuthenticationException(logMessage);
        }
    }
}
