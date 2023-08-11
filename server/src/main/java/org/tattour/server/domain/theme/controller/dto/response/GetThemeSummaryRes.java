package org.tattour.server.domain.theme.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeSummaryRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetThemeSummaryRes {

    @Schema(description = "테마 Id")
    private Integer id;

    @Schema(description = "테마 이름")
    private String name;

    public static GetThemeSummaryRes from(ReadThemeSummaryRes readThemeSummaryRes) {
        return GetThemeSummaryRes.builder()
                .id(readThemeSummaryRes.getId())
                .name(readThemeSummaryRes.getName())
                .build();
    }
}