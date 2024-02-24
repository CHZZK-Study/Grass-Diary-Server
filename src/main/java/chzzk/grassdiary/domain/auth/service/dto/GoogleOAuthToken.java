package chzzk.grassdiary.domain.auth.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// JSON 직렬화(object -> JSON) 및 역직렬화(JSON -> object) 시 사용되는 네이밍 전략 설정 어노테이션
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GoogleOAuthToken(
        String accessToken,
        String refreshToken
) {
}
