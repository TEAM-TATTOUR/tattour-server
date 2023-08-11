package org.tattour.server.domain.theme.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeListRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetThemeListRes {

    @Schema(description = "테마 정보")
    List<GetThemeRes> themeInfos;

    public static GetThemeListRes from(ReadThemeListRes readThemeListRes) {
        return new GetThemeListRes(
            readThemeListRes
                .getThemeInfos()
                .stream()
                .map(GetThemeRes::from)
                .collect(Collectors.toList()));
    }
}
