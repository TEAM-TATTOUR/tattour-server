package org.tattour.server.domain.magazine.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineUrlRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "메거진 url 조회 응답")
public class GetMagazineUrlRes {

	@Schema(description = "매거진 url")
	private String magazineUrl;

	public static GetMagazineUrlRes from(ReadMagazineUrlRes readMagazineUrlRes) {
		return new GetMagazineUrlRes(readMagazineUrlRes.getMagazineUrl());
	}
}
