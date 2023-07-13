package org.tattour.server.domain.custom.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.custom.exception.NotFoundCustomSizeException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CustomSize {

	SIZE_1("size1"), SIZE_2("size2"), SIZE_3("size3"), SIZE_4("size4");

	private final String value;

	public static CustomSize getCustomSize(String value) {
		switch (value) {
			case "size1" :
				return CustomSize.SIZE_1;
			case "size2" :
				return CustomSize.SIZE_2;
			case "size3" :
				return CustomSize.SIZE_3;
			case "size4" :
				return CustomSize.SIZE_4;
			default:
				throw new NotFoundCustomSizeException();
		}
	}
}
