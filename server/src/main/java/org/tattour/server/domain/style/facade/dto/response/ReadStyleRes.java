package org.tattour.server.domain.style.facade.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadStyleRes {

    private Integer id;
    private String imageUrl;
    private String name;
    private String description;

    public static ReadStyleRes from(Style style) {
        return ReadStyleRes.builder()
                .id(style.getId())
                .imageUrl(style.getImageUrl())
                .name(style.getName())
                .description(style.getDescription())
                .build();
    }
}
