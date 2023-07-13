package org.tattour.server.domain.theme.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemeInfoList {

	List<ThemeInfo> themeInfos;

	public static ThemeInfoList of(List<Theme> themes) {
		List<ThemeInfo> themeInfos = themes.stream()
			.map(ThemeInfo::of)
			.collect(Collectors.toList());
		return new ThemeInfoList(themeInfos);
	}
}
