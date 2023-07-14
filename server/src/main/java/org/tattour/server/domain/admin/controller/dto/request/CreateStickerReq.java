package org.tattour.server.domain.admin.controller.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.sticker.service.dto.request.CreateStickerInfo;

@Getter
@Setter
@NoArgsConstructor
public class CreateStickerReq {

	@NotNull
	private String name;

	@NotNull
	private Boolean isCustom;
	@NotNull
	private Integer price;
	private String composition;
	@NotNull
	private String size;
	@NotNull
	private Integer shippingFee;
	@NotNull
	private List<Integer> themes;
	@NotNull
	private List<Integer> styles;
	private String description;

	public CreateStickerInfo newCreateStickerInfo(MultipartFile mainImage, List<MultipartFile> images) {
		return CreateStickerInfo.from(name, mainImage, images, isCustom, price, composition,
			size, shippingFee, themes, styles, description);
	}
}
