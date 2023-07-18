package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Schema(description = "user 정보 수정 Request")
@Getter
public class PatchUserInfoReq {
    @Schema(description = "이름", example = "userName")
    @NotBlank(message = "name is required")
    private String name;

    @Schema(description = "전화번호", example = "01012345678")
    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "\\d+", message = "Only number is allowed")
    private String phoneNumber;
}
