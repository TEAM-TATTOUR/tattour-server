package org.tattour.server.domain.style.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StyleInfoList {

    List<StyleInfo> styleInfos;

    public static StyleInfoList of(List<Style> styles) {
        List<StyleInfo> styleInfos = styles.stream()
                .map(StyleInfo::of)
                .collect(Collectors.toList());
        return new StyleInfoList(styleInfos);
    }

}
