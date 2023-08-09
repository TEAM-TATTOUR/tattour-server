package org.tattour.server.domain.style.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleSummaryRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetStyleSummaryRes {

    @Schema(description = "스타일 ID")
    private Integer id;

    @Schema(description = "스타일 이름")
    private String name;

    public static GetStyleSummaryRes from(ReadStyleSummaryRes readStyleRes) {
        return GetStyleSummaryRes.builder()
                .id(readStyleRes.getId())
                .name(readStyleRes.getName())
                .build();
    }
}
