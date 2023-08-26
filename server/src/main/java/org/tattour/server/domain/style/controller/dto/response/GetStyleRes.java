package org.tattour.server.domain.style.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetStyleRes {

    @Schema(description = "스타일 ID")
    private Integer id;

    @Schema(description = "스타일 이미지 URL")
    private String imageUrl;

    @Schema(description = "스타일 이름")
    private String name;

    @Schema(description = "스타일 설명")
    private String description;

    public static GetStyleRes from(ReadStyleRes readStyleRes) {
        return GetStyleRes.builder()
                .id(readStyleRes.getId())
                .imageUrl(readStyleRes.getImageUrl())
                .name(readStyleRes.getName())
                .description(readStyleRes.getDescription())
                .build();
    }
}
