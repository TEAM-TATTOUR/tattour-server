package org.tattour.server.domain.magazine.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineListRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "메거진 전체 조회 응답")
public class GetMagazineListRes {

	@Schema(description = "매거진 리스트")
	List<GetMagazineRes> magazines;

	static public GetMagazineListRes from(ReadMagazineListRes readMagazineListRes) {
		return new GetMagazineListRes(
				readMagazineListRes
						.getMagazines()
						.stream()
						.map(GetMagazineRes::from)
						.collect(Collectors.toList()));
	}

}
