package org.tattour.server.domain.style.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.model.Style;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadStyleListRes {

    List<ReadStyleRes> readStyleRes;

    public static ReadStyleListRes from(List<Style> styles) {
        return new ReadStyleListRes(
                styles
                        .stream()
                        .map(ReadStyleRes::from)
                        .collect(Collectors.toList()));
    }

}
