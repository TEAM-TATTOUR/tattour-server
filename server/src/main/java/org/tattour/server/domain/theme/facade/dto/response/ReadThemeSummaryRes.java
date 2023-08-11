package org.tattour.server.domain.theme.facade.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadThemeSummaryRes {

    private Integer id;
    private String name;

    public static ReadThemeSummaryRes from(Theme theme) {
        return ReadThemeSummaryRes.builder()
                .id(theme.getId())
                .name(theme.getName())
                .build();
    }
}