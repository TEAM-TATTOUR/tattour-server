package org.tattour.server.domain.theme.facade.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.model.Theme;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadThemeRes {

    private Integer id;
    private String imageUrl;
    private String name;
    private String description;

    public static ReadThemeRes from(Theme theme) {
        return ReadThemeRes.builder()
                .id(theme.getId())
                .imageUrl(theme.getImageUrl())
                .name(theme.getName())
                .description(theme.getDescription())
                .build();
    }

}
