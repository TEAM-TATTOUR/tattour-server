package org.tattour.server.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.admin.controller.dto.request.ConfirmPointChargeReq;
import org.tattour.server.domain.admin.controller.dto.request.CreateStickerReq;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API Document")
public class AdminController {

	private final OrderProviderImpl orderProvider;
	private final PointProviderImpl pointProvider;
	private final PointServiceImpl pointService;
	private final StickerService stickerService;
	private final JwtService jwtService;

	// TODO : ADMIN jwt 확인
	@Operation(summary = "모든 결제 내역 불러오기")
	@GetMapping("/order")
	public ResponseEntity<?> getOrderHistory(
		@RequestParam("page") int page
	) {
		return ApiResponse.success(SuccessType.GET_SUCCESS, orderProvider.getOrderHistory(page));
	}

	@Operation(summary = "포인트 충전 신청 내역 불러오기")
	@GetMapping("/point/request")
	public ResponseEntity<?> getPointChargeRequest(
		@RequestParam(required = false) Integer userId,
		@RequestParam(required = false) Boolean isCompleted
	) {
		return ApiResponse.success(SuccessType.GET_SUCCESS,
			pointProvider.getAllPointChargeRequest(userId, isCompleted));
	}

	@Operation(summary = "포인트 충전 요청 상태 확정")
	@PostMapping("/point/request/confirm")
	public ResponseEntity<?> confirmPointChargeRequest(
		@RequestBody ConfirmPointChargeReq req
	) {
		pointService.confirmPointChargeRequest(
			ConfirmPointChargeRequestReq.of(req.getId(), req.isApproved()));
		return ApiResponse.success(SuccessType.POINT_CHARGE_CONFIRM_SUCCESS);
	}

	@PostMapping(value = "/stickers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "스티커 등록")
	public ResponseEntity<?> getSimilarStickerList(
		 @Parameter(hidden = true) @UserId Integer userId,
		@Parameter(description = "content-type을 application/json 타입으로 보내기",
			style = ParameterStyle.FORM) @Valid CreateStickerReq stickerInfo,
		@RequestPart MultipartFile stickerMainImage,
		@RequestPart(required = false) List<MultipartFile> stickerImages
	) {
		jwtService.compareJwtWithPathVar(1, 1);
		Integer response = stickerService.createSticker(stickerInfo.newCreateStickerInfo(stickerMainImage, stickerImages));
		return ApiResponse.success(SuccessType.CREATE_SUCCESS, response);
	}
}
