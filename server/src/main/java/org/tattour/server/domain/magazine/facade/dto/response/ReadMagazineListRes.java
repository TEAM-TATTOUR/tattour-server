package org.tattour.server.domain.magazine.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.magazine.domain.Magazine;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadMagazineListRes {

	private List<ReadMagazineRes> magazines;

	public static ReadMagazineListRes from(List<Magazine> magazines) {
		return new ReadMagazineListRes(
				magazines
						.stream()
						.map(ReadMagazineRes::from)
						.collect(Collectors.toList()));
	}
}
