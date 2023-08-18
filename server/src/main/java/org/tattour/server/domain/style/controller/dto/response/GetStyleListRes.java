package org.tattour.server.domain.style.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleListRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStyleListRes {

	@Schema(description = "스타일 리스트")
	List<GetStyleRes> styleInfos;

	public static GetStyleListRes from(ReadStyleListRes readStyleListRes) {
		return new GetStyleListRes(
				readStyleListRes
						.getReadStyleRes()
						.stream()
						.map(GetStyleRes::from)
						.collect(Collectors.toList()));
	}

}
