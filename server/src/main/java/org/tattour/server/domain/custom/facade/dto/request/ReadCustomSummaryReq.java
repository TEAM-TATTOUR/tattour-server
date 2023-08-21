package org.tattour.server.domain.custom.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadCustomSummaryReq {

	private Integer userId;
	private String date;

	public static ReadCustomSummaryReq of(
			Integer userId,
			String date) {
		return new ReadCustomSummaryReq(userId, date);
	}
}
