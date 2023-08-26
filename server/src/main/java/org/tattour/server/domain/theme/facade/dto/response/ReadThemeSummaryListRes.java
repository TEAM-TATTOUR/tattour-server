package org.tattour.server.domain.theme.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadThemeSummaryListRes {

	List<ReadThemeSummaryRes> themeSummaries;

	public static ReadThemeSummaryListRes from(List<Theme> themes) {
		return new ReadThemeSummaryListRes(
				themes
						.stream()
						.map(ReadThemeSummaryRes::from)
						.collect(Collectors.toList()));
	}
}
