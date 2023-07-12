package org.tattour.server.domain.custom.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Process {

	receiving("receiving"),
	receiptComplete("receiptComplete"),
	receiptFailed("receiptFailed"),
	shipping("shipping"),
	shipped("shipped");

	private String name;
}
