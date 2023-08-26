package org.tattour.server.domain.custom.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchCustomRes {

	@Schema(description = "커스텀 아이디")
	private Integer id;

	@Schema(description = "유저 아이디")
	private Integer userId;

	@Schema(description = " null 값 가능 ")
	private Integer stickerId;

	@Schema(description = "테마 이름 리스트")
	private List<String> themes;

	@Schema(description = "스타일 이름 리스트")
	private List<String> styles;

	@Schema(description = "커스텀 신청 메인 이미지")
	private String mainImageUrl;

	@Schema(description = "커스텀 신청 손그림 이미지")
	private String handDrawingImageUrl;

	@Schema(description = "커스텀 신청 이미지들, null 값 가능")
	private List<String> images;

	@Schema(description = "커스텀 신청 도안 보유 여부")
	private Boolean haveDesign;

	@Schema(example = "quarter, half, regular, double")
	private String size;

	@Schema(description = "커스텀 신청 이름")
	private String name;

	@Schema(description = "커스텀 신청 설명")
	private String description;

	@Schema(description = "커스텀 신청 요구사항")
	private String demand;

	@Schema(description = "커스텀 신청 수량")
	private Integer count;

	@Schema(description = "커스텀 신청 컬러 여부")
	private Boolean isColored;

	@Schema(description = "커스텀 신청 공개 여부")
	private Boolean isPublic;

	@Schema(description = "커스텀 신청 완료 여부")
	private Boolean isCompleted;

	@Schema(description = "커스텀 신청 가격")
	private Integer price;

	@Schema(description = "커스텀 신청 진행 상태", example = "receiving, receiptComplete, receiptFailed, shipping, shipped")
	private String process;

	@Schema(description = "커스텀 신청 조회수")
	private Integer viewCount;

	public static PatchCustomRes from(ReadCustomRes readCustomRes) {
		return PatchCustomRes.builder()
				.id(readCustomRes.getId())
				.userId(readCustomRes.getUserId())
				.stickerId(readCustomRes.getStickerId())
				.themes(readCustomRes.getThemes())
				.styles(readCustomRes.getStyles())
				.mainImageUrl(readCustomRes.getMainImageUrl())
				.handDrawingImageUrl(readCustomRes.getHandDrawingImageUrl())
				.images(readCustomRes.getImages())
				.haveDesign(readCustomRes.getHaveDesign())
				.size(readCustomRes.getSize())
				.name(readCustomRes.getName())
				.description(readCustomRes.getDescription())
				.demand(readCustomRes.getDemand())
				.count(readCustomRes.getCount())
				.isColored(readCustomRes.getIsColored())
				.isPublic(readCustomRes.getIsPublic())
				.isCompleted(readCustomRes.getIsCompleted())
				.price(readCustomRes.getPrice())
				.process(readCustomRes.getProcess())
				.viewCount(readCustomRes.getViewCount())
				.build();
	}

}
