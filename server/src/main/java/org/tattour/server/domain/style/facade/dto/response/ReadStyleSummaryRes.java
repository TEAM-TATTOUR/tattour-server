package org.tattour.server.domain.style.facade.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadStyleSummaryRes {

    private Integer id;
    private String name;

    public static ReadStyleSummaryRes from(Style style) {
        return ReadStyleSummaryRes.builder()
                .id(style.getId())
                .name(style.getName())
                .build();
    }
}
