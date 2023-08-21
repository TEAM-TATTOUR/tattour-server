package org.tattour.server.domain.user.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "user 정보")
@Getter
@Setter
public class UserContactInfo {

    @Schema(description = "user id")
    private Integer id;

    @Schema(description = "이름", example = "userName")
    private String name;

    @Schema(description = "전화번호", example = "01012345678")
    private String phoneNumber;
}
