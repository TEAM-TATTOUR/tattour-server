package org.tattour.server.domain.custom.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadCustomSummaryRes {

	private Integer userId;
	private String date;

	public static ReadCustomSummaryRes of(
			Integer userId,
			String date) {
		return new ReadCustomSummaryRes(userId, date);
	}
}
