package org.tattour.server.global.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "서버 Profile 확인")
public class ServerProfileController {

    private final Environment env;

    @GetMapping("/profile")
    @Operation(summary = "Server application 파일 profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}