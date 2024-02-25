package chzzk.grassdiary.domain.auth.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void 액세스_토큰을_생성한다() {
        // given
        String id = "290940394";

        // when
        String accessToken = jwtTokenProvider.generateAccessTokenById(id);

        // then
        System.out.println("accessToken = " + accessToken);
        assertThat(accessToken).isNotNull();
    }
}