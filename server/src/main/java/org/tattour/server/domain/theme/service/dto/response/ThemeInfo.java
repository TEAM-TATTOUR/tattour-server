package org.tattour.server.domain.theme.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ThemeInfo {

    private Integer id;
    private String imageUrl;
    private String name;
    private String description;

    public static ThemeInfo of(Theme theme) {
        return ThemeInfo.builder()
                .id(theme.getId())
                .imageUrl(theme.getImageUrl())
                .name(theme.getName())
                .description(theme.getDescription())
                .build();
    }

}
