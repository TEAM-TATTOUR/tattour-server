package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.sticker.facade.dto.request.CreateStickerReq;

@Getter
@NoArgsConstructor
public class PostStickerReq {

	@NotBlank(message = "name is required")
	@Size(max = 20, message = "name is max 20")
	private String name;

	@NotNull(message = "isCustom is required")
	@Schema(description = "커스텀 신청 스티커인지")
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
	@Schema(description = "테마 id 리스트")
	private List<Integer> themes;

	@NotNull(message = "styles is null")
	@Schema(description = "스타일 id 리스트")
	private List<Integer> styles;

	@Size(max = 255, message = "description is max 50")
	private String description;

    public CreateStickerReq newCreateStickerReq(
			MultipartFile mainImage,
            List<MultipartFile> images) {
        return CreateStickerReq.of(name, mainImage, images, isCustom, price, composition,
                size, shippingFee, themes, styles, description);
    }
}
