package org.tattour.server.domain.style.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStyleListRes {

	@Schema(description = "스타일 리스트")
	List<GetStyleRes> readStyleRes;

	public static GetStyleListRes from(List<ReadStyleRes> readStyleResList) {
		return new GetStyleListRes(
			readStyleResList
				.stream()
				.map(GetStyleRes::from)
				.collect(Collectors.toList()));
	}

}
