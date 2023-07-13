package org.tattour.server.domain.custom.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.custom.exception.NotFoundCustomSizeException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CustomSize {

	QUARTER("quarter", 4000, 1000),
	HALF("half", 5000, 1000),
	REGULAR("regular", 6500, 1500),
	DOUBLE("double", 10000, 2000);

	private final String size;
	private final int price;
	private final int discountPrice;

	public static CustomSize getCustomSize(String size) {
		switch (size) {
			case "quarter":
				return CustomSize.QUARTER;
			case "half":
				return CustomSize.HALF;
			case "regular":
				return CustomSize.REGULAR;
			case "double":
				return CustomSize.DOUBLE;
			default:
				throw new NotFoundCustomSizeException();
		}
	}
}
