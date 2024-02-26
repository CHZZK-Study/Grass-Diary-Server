package chzzk.grassdiary.domain.auth.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 구글 서버로부터 받은 사용자 정보
 */
public record GoogleUserInfo(
        String id,

        @JsonProperty("email")
        String email,

        @JsonProperty("given_name")
        String nickname,

        @JsonProperty("picture")
        String picture
) {
}
