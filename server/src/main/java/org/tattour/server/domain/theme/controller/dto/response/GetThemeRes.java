package org.tattour.server.domain.theme.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetThemeRes {

    @Schema(description = "테마 Id")
    private Integer id;

    @Schema(description = "테마 이미지 Url")
    private String imageUrl;

    @Schema(description = "테마 이름")
    private String name;

    @Schema(description = "테마 설명")
    private String description;

    public static GetThemeRes from(ReadThemeRes readThemeRes) {
        return GetThemeRes.builder()
                .id(readThemeRes.getId())
                .imageUrl(readThemeRes.getImageUrl())
                .name(readThemeRes.getName())
                .description(readThemeRes.getDescription())
                .build();
    }

}
