package org.tattour.server.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.admin.controller.dto.request.ConfirmPointChargeRequestReq;
import org.tattour.server.domain.admin.controller.dto.request.CreateStickerReq;
import org.tattour.server.domain.admin.controller.dto.request.UpdateCustomProcessReq;
import org.tattour.server.domain.admin.controller.dto.response.CreateStickerRes;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.domain.order.controller.dto.request.PatchOrderStatusReq;
import org.tattour.server.domain.order.provider.dto.response.GetOrderHistoryListRes;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.point.provider.dto.request.GetPointLogListReq;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
import org.tattour.server.domain.point.provider.dto.response.GetPointLogListRes;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestDto;
import org.tattour.server.domain.order.service.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.point.service.dto.response.ConfirmPointChargeResponseDto;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Admin", description = "Admin API Document")
public class AdminController {

	private final OrderProviderImpl orderProvider;
	private final PointProviderImpl pointProvider;
	private final PointServiceImpl pointService;
	private final OrderServiceImpl orderService;
	private final JwtService jwtService;
	private final StickerService stickerService;
	private final CustomService customService;

	// TODO : ADMIN role 확인
	@Operation(summary = "모든 결제내역 불러오기", description = "모든 결제내역 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "로그인에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = LoginRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/order")
	public ResponseEntity<?> getOrderHistory(
		@Parameter(description = "페이지 넘버", required = true) @RequestParam("page") int page
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
			orderProvider.getOrderHistoryByPage(page));
	}

	@Operation(summary = "포인트 충전 신청 내역 불러오기")
	@GetMapping("/point/request")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success",
			content = @Content(schema = @Schema(implementation = GetPointChargeRequestListRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getPointChargeRequest(
		@RequestParam(required = false) Integer userId,
		@RequestParam(required = false) Boolean isCompleted
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
			pointProvider.getAllPointChargeRequest(userId, isCompleted));
	}

	@Operation(summary = "포인트 충전 요청 확인")
	@PostMapping("/point/request/confirm")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "포인트 충전 완료"),
		@ApiResponse(responseCode = "202", description = "포인트 충전 취소",
			content = @Content(schema = @Schema(implementation = ConfirmPointChargeResponseDto.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> confirmPointChargeRequest(
		@RequestBody @Valid ConfirmPointChargeRequestReq req
	) {
		ConfirmPointChargeResponseDto response = pointService.confirmPointChargeRequest(
			ConfirmPointChargeRequestDto.of(req.getId(), req.getUserId(),
				req.getTransferredAmount()));
		if (Objects.isNull(response)) {
			return BaseResponse.success(SuccessType.POINT_CHARGE_CONFIRM_SUCCESS);
		} else {
			return BaseResponse.success(SuccessType.POINT_CHARGE_CONFIRM_FAIL, response);
		}
	}

	@Operation(summary = "포인트 충전 요청 취소")
	@PostMapping("/point/request/cancel")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "success"),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> cancelPointChargeRequest(
		@RequestBody @Valid CancelPointChargeRequestReq req
	) {
		pointService.cancelPointChargeRequest(req);
//                CancelPointChargeRequestReq.of(req.getId(), req.getUserId(), req.getTransferredAmount(), req.getReason()));
		return BaseResponse.success(SuccessType.POINT_CHARGE_CANCEL_SUCCESS);
	}

	@Operation(summary = "포인트 로그 불러오기")
	@GetMapping("/pointLog")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success",
			content = @Content(schema = @Schema(implementation = GetPointLogListRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getPointLog(
		@RequestParam(required = false) Integer userId,
		@RequestParam(required = false) String title
	) {
		return BaseResponse.success(
			SuccessType.READ_POINT_LOG_SUCCESS,
			pointProvider.getPointLog(GetPointLogListReq.of(userId, title)));
	}

	@Operation(summary = "결제 내역 주문상태 변경")
	@PatchMapping("/order/{orderId}/status")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> patchOrderStatus(
		@PathVariable int orderId,
		@RequestBody @Valid PatchOrderStatusReq req
	) {
		orderService.updateOrderStatus(UpdateOrderStatusReq.of(orderId, req.getOrderStatus()));

		return BaseResponse.success(SuccessType.UPDATE_ORDER_STATUS_SUCCESS);
	}

	@PostMapping(value = "/stickers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "스티커 등록")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "success",
			content = @Content(schema = @Schema(implementation = CreateStickerRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getSimilarStickerList(
		@Parameter(hidden = true) @UserId Integer userId,
		@Parameter(description = "content-type을 application/json 타입으로 보내기")
		@RequestPart(value = "stickerInfo") @Valid CreateStickerReq stickerInfo,
		@RequestPart(value = "stickerMainImage") MultipartFile stickerMainImage,
		@RequestPart(value = "stickerImages", required = false) List<MultipartFile> stickerImages
	) {
		jwtService.compareJwtWithPathVar(userId, 1);
		CreateStickerRes response = new CreateStickerRes(stickerService.createSticker(
			stickerInfo.newCreateStickerInfo(stickerMainImage, stickerImages)));
		return BaseResponse.success(SuccessType.CREATE_SUCCESS, response);
	}

	@PostMapping(value = "/custom/recieve/{customId}")
	@Operation(summary = "커스텀 도안 상태 변경하기",
		description = "process : <receiving, receiptComplete, receiptFailed, shipping, shipped>")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success",
			content = @Content(schema = @Schema(implementation = CustomInfo.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getSimilarStickerList(
		@Parameter(hidden = true) @UserId Integer userId,
		@RequestBody UpdateCustomProcessReq request
	) {
		CustomInfo response = customService.updateCustomProcess(request.newUpdateCustomInfo(userId));
		return BaseResponse.success(SuccessType.UPDATE_CUSTOM_PROCESS_SUCCESS, response);
	}

}