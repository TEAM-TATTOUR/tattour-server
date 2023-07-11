package org.tattour.server.domain.custom.service.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CustomInfo {


	private String name;
	private List<String> customThemes;
	private List<String> customStyles;
	private List<String> images;
	private Boolean isColored;
//	private Integer

//	public Custom newCustom() {
//		return Custom.from()
//	}

}
