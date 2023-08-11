package org.tattour.server.domain.user.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

@Schema(description = "user profile 정보 Response")
@Getter
@Setter
@AllArgsConstructor
public class ReadUserProfileRes {

    private UserProfileInfo userProfileInfo;

    public static ReadUserProfileRes of(UserProfileInfo userProfileInfo) {
        return new ReadUserProfileRes(userProfileInfo);
    }
}
