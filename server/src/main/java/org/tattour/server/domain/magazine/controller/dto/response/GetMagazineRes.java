package org.tattour.server.domain.magazine.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMagazineRes {

	@Schema(description = "매거진 id")
	private Integer id;

	@Schema(description = "매거진 url")
	private String magazineUrl;

	@Schema(description = "매거진 이미지 url")
	private String magazineImageUrl;

	public static GetMagazineRes from(ReadMagazineRes readMagazineRes) {
		return new GetMagazineRes(
				readMagazineRes.getId(),
				readMagazineRes.getMagazineUrl(),
				readMagazineRes.getMagazineImageUrl());
	}
}
