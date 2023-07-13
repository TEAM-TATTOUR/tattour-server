package org.tattour.server.domain.custom.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.custom.exception.NotFoundCustomProcessException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CustomProcess {

	RECEIVING("receiving"),
	RECEIPT_COMPLETE("receiptComplete"),
	RECEIPT_FAILED("receiptFailed"),
	SHIPPING("shipping"),
	SHIPPED("shipped");

	private final String value;

	public static CustomProcess getProcess(String value) {
		switch (value) {
			case "receiving":
				return CustomProcess.RECEIVING;
			case "receiptComplete":
				return CustomProcess.RECEIPT_COMPLETE;
			case "receiptFailed":
				return CustomProcess.RECEIPT_FAILED;
			case "shipping":
				return CustomProcess.SHIPPING;
			case "shipped":
				return CustomProcess.SHIPPED;
			default:
				throw new NotFoundCustomProcessException();
		}
	}
}
