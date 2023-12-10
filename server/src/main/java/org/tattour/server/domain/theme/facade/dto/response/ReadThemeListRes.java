package org.tattour.server.domain.theme.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.model.Theme;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadThemeListRes {

    List<ReadThemeRes> themeInfos;

    public static ReadThemeListRes from(List<Theme> themes) {
        return new ReadThemeListRes(
                themes
                        .stream()
                        .map(ReadThemeRes::from)
                        .collect(Collectors.toList()));
    }
}
