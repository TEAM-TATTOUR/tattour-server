package org.tattour.server.domain.style.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class StyleInfo {

    private Integer id;
    private String imageUrl;
    private String name;
    private String description;

    public static StyleInfo of(Style style) {
        return StyleInfo.builder()
                .id(style.getId())
                .imageUrl(style.getImageUrl())
                .name(style.getName())
                .description(style.getDescription())
                .build();
    }
}
