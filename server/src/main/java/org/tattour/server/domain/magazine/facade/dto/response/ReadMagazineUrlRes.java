package org.tattour.server.domain.magazine.facade.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.model.Magazine;

@Getter
@AllArgsConstructor
public class ReadMagazineUrlRes {

	private String magazineUrl;

	public static ReadMagazineUrlRes from(Magazine magazine) {
		return new ReadMagazineUrlRes(magazine.getMagazineUrl());
	}
}
