package chzzk.grassdiary.domain.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 토큰 Payload에 저장될 정보
 * <p>
 * -> email, 생성일, 만료일
 */
@Component
public class JwtTokenProvider {
    @Value("${jwt.access.secret-key}")
    private String jwtAccessTokenSecretKey; // JWT signature를 생성할 때, 사용되는 secretKey

    @Value("${jwt.access.expiration}")
    private long jwtAccessTokenExpiration; // 액세스 토큰의 만료 시간

    /**
     * 액세스 토큰 생성
     *
     * @param email
     * @return
     */
    public String generateAccessToken(String email) {
        long currentDateTime = new Date().getTime();
        Date expiryDate = new Date(currentDateTime + jwtAccessTokenExpiration);
        SecretKeySpec secretKey = new SecretKeySpec(
                jwtAccessTokenSecretKey.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .claim("email", email)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 스프링 서버는 브라우저로부터 받은 액세스 토큰으로부터 이메일을 추출하여 인증
     */

    /**
     * 액세스 토큰 검증 - interceptor
     */

    /**
     * email 확인 메소드, 만료일 확인 메소드
     */
}