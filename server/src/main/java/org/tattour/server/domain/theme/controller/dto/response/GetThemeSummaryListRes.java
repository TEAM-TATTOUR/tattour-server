package org.tattour.server.domain.theme.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeSummaryListRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetThemeSummaryListRes {

    @Schema(description = "테마 요약 정보")
    List<GetThemeSummaryRes> themeSummaries;

    public static GetThemeSummaryListRes from(ReadThemeSummaryListRes readThemeSummaryListRes) {
        return new GetThemeSummaryListRes(
            readThemeSummaryListRes
                .getThemeSummaries()
                .stream()
                .map(GetThemeSummaryRes::from)
                .collect(Collectors.toList()));
    }
}
