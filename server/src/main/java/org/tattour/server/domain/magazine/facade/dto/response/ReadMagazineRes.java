package org.tattour.server.domain.magazine.facade.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.domain.Magazine;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ReadMagazineRes {

	private Integer id;
	private String magazineUrl;
	private String magazineImageUrl;

	public static ReadMagazineRes from(Magazine magazine) {
		return new ReadMagazineRes(
				magazine.getId(),
				magazine.getMagazineUrl(),
				magazine.getMagazineImageUrl()
		);
	}
}
