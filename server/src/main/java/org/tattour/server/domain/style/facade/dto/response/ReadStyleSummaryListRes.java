package org.tattour.server.domain.style.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.model.Style;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadStyleSummaryListRes {

    List<ReadStyleSummaryRes> styleSummaries;

    public static ReadStyleSummaryListRes from(List<Style> styles) {
        return new ReadStyleSummaryListRes(
                styles
                        .stream()
                        .map(ReadStyleSummaryRes::from)
                        .collect(Collectors.toList()));
    }

}
