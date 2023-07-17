package org.tattour.server.domain.admin.controller.dto.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.sticker.service.dto.request.CreateStickerInfo;

@Getter
@Setter
@NoArgsConstructor
public class CreateStickerReq {

	@NotBlank(message = "name is required")
	@Size(max = 20, message = "name is max 20")
	private String name;

	@NotNull(message = "isCustom is required")
	private Boolean isCustom;

	@NotNull(message = "price is null")
	private Integer price;

	@Size(max = 20, message = "composition is max 20")
	private String composition;

	@NotBlank(message = "size is required")
	private String size;

	@NotNull(message = "shippingFee is null")
	private Integer shippingFee;

	@NotNull(message = "themes is null")
	private List<Integer> themes;

	@NotNull(message = "styles is null")
	private List<Integer> styles;

	@Size(max = 50, message = "description is max 50")
	private String description;

	public CreateStickerInfo newCreateStickerInfo(MultipartFile mainImage, List<MultipartFile> images) {
		return CreateStickerInfo.from(name, mainImage, images, isCustom, price, composition,
			size, shippingFee, themes, styles, description);
	}
}
