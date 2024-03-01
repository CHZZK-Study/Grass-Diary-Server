package chzzk.grassdiary.domain.auth.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleOAuthToken(
        @JsonProperty("access_token")
        String accessToken
) {
}
