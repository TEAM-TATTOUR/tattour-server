package org.tattour.server.domain.user.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(name = "유저 프로필 정보")
@Builder
@Getter
public class UserProfileRes {

    @Schema(description = "user Id")
    private int id;

    @Schema(description = "user 이름", example = "김타투")
    private String name;

    @Schema(description = "user 전화번호", example = "01012345678")
    private String phoneNumber;
}
