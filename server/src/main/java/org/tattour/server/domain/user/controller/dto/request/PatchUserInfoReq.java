package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PatchUserInfoReq {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "\\d+", message = "Only number is allowed")
    private String phoneNumber;
}
