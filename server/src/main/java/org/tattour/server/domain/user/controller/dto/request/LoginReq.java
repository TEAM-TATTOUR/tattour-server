package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@Schema(description = "로그인 Request")
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginReq {

    @Schema(description = "소셜 로그인 플랫폼", example = "KAKAO")
    @NotNull(message = "socialPlatform is null")
    private SocialPlatform socialPlatform;

    public static LoginReq of(SocialPlatform socialPlatform) {
        return new LoginReq(socialPlatform);
    }
}
