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
import org.tattour.server.domain.admin.controller.dto.request.ApplyStickerDiscountReq;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.admin.controller.dto.request.ConfirmPointChargeRequestReq;
import org.tattour.server.domain.admin.controller.dto.request.PostStickerReq;
import org.tattour.server.domain.admin.controller.dto.request.PostDiscountReq;
import org.tattour.server.domain.admin.controller.dto.request.PatchCustomProcessReq;
import org.tattour.server.domain.admin.controller.dto.response.PostStickerRes;
import org.tattour.server.domain.custom.facade.CustomFacade;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.discount.facade.DiscountFacade;
import org.tattour.server.domain.order.controller.dto.request.PatchOrderStatusReq;
import org.tattour.server.domain.order.facade.OrderFacade;
import org.tattour.server.domain.point.domain.PointLogCategory;
import org.tattour.server.domain.point.facade.PointFacade;
import org.tattour.server.domain.point.facade.dto.request.ReadPointChargeRequestListReq;
import org.tattour.server.domain.point.facade.dto.request.ReadPointLogListReq;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointLogListRes;
import org.tattour.server.domain.point.facade.dto.request.ConfirmPointChargeReq;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.point.facade.dto.response.ConfirmPointChargeRes;
import org.tattour.server.domain.sticker.facade.StickerFacade;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerRes;
import org.tattour.server.domain.user.controller.dto.response.PostLoginRes;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.annotations.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Admin", description = "Admin API Document")
public class AdminController {

	private final JwtService jwtService;
	private final PointFacade pointFacade;
	private final OrderFacade orderFacade;
	private final DiscountFacade discountFacade;
	private final StickerFacade stickerFacade;
	private final CustomFacade customFacade;

	// TODO : ADMIN role 확인
	@Operation(summary = "모든 결제내역 불러오기", description = "모든 결제내역 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = PostLoginRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/order/history")
	public ResponseEntity<?> getOrderHistory(
			@Parameter(description = "페이지 넘버", required = true) @RequestParam("page") int page
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				orderFacade.readOrderHistoryOnPage(page));
	}


	@Operation(summary = "주문내역 상태 변경")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "주문상태 변경에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 결제내역입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "409",
					description = "이미 취소 처리된 결제 내역입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PatchMapping("/order/{orderId}/status")
	public ResponseEntity<?> patchOrderStatus(
			@Parameter(description = "주문내역 id", required = true) @PathVariable int orderId,
			@RequestBody @Valid PatchOrderStatusReq req
	) {
		orderFacade.updateOrderStatus(UpdateOrderStatusReq.of(orderId, req.getOrderStatus()));

		return BaseResponse.success(SuccessType.UPDATE_ORDER_STATUS_SUCCESS);
	}


	@Operation(summary = "포인트 충전 신청 내역 불러오기", description = "userId, 완료 여부를 기준으로 포인트 신청 내역 조회")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ReadPointChargeRequestListRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/point/request")
	public ResponseEntity<?> getPointChargeRequest(
			@Parameter(description = "user Id") @RequestParam(required = false) Integer userId,
			@Parameter(description = "처리 완료 여부") @RequestParam(required = false) Boolean isCompleted
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				pointFacade.readPointChargeRequest(
						ReadPointChargeRequestListReq.of(
								userId,
								isCompleted)));
	}


	@Operation(summary = "포인트 충전 요청 확인")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "포인트 충전 확정에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "202",
					description = "금액이 일치하지 않아 충전 확정이 불가능합니다.",
					content = @Content(schema = @Schema(implementation = ConfirmPointChargeRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 포인트 충전 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "409",
					description = "이미 처리된 포인트 충전 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/point/request/confirm")
	public ResponseEntity<?> confirmPointChargeRequest(
			@RequestBody @Valid ConfirmPointChargeRequestReq req
	) {
		ConfirmPointChargeRes response = pointFacade.confirmPointChargeRequest(
				ConfirmPointChargeReq.of(
						req.getId(),
						req.getUserId(),
						req.getTransferredAmount()));

		if (Objects.isNull(response)) {
			return BaseResponse.success(SuccessType.POINT_CHARGE_CONFIRM_SUCCESS);
		} else {
			return BaseResponse.success(SuccessType.POINT_CHARGE_CONFIRM_FAIL, response);
		}
	}


	@Operation(summary = "포인트 충전 요청 취소")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "포인트 충전 취소에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 포인트 충전 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "409",
					description = "이미 처리된 포인트 충전 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "409",
					description = "송금 금액과 충전 금액이 일치합니다. 충전 요청을 취소할 수 없습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/point/request/cancel")
	public ResponseEntity<?> cancelPointChargeRequest(
			@RequestBody @Valid CancelPointChargeRequestReq req
	) {
		pointFacade.cancelPointChargeRequest(req);
		return BaseResponse.success(SuccessType.POINT_CHARGE_CANCEL_SUCCESS);
	}


	@Operation(summary = "포인트 로그 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "포인트 로그 조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ReadPointLogListRes.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/pointlog")
	public ResponseEntity<?> getPointLog(
			@Parameter(description = "user id") @RequestParam(required = false) Integer userId,
			@Parameter(description = "포인트 로그 카테고리", example = "충전 취소")
			@RequestParam(required = false) String category
	) {
		return BaseResponse.success(
				SuccessType.READ_POINT_LOG_SUCCESS, pointFacade.readPointLog(
						ReadPointLogListReq.of(
								userId,
								PointLogCategory.valueOf(category))));
	}


	@PostMapping(value = "/stickers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "스티커 등록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "success",
					content = @Content(schema = @Schema(implementation = PostStickerRes.class))),
			@ApiResponse(responseCode = "400, 500", description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> createSticker(
			@Parameter(hidden = true) @UserId Integer userId,
			@Parameter(description = "content-type을 application/json 타입으로 보내기")
			@RequestPart(value = "stickerInfo") @Valid PostStickerReq stickerInfo,
			@RequestPart(value = "stickerMainImage") MultipartFile stickerMainImage,
			@RequestPart(value = "stickerImages", required = false) List<MultipartFile> stickerImages
	) {
		jwtService.compareJwtWithPathVar(userId, 1);
		PostStickerRes response =
				PostStickerRes.of(
						stickerFacade.createSticker(
								stickerInfo.newCreateStickerReq(
										stickerMainImage,
										stickerImages)));
		return BaseResponse.success(SuccessType.CREATE_SUCCESS, response);
	}


	@PostMapping(value = "/custom/recieve/{customId}")
	@Operation(summary = "커스텀 도안 상태 변경하기",
			description = "process : <receiving, receiptComplete, receiptFailed, shipping, shipped>")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "success",
					content = @Content(schema = @Schema(implementation = ReadCustomRes.class))),
			@ApiResponse(responseCode = "400, 500", description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> updateCustomProcess(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PatchCustomProcessReq request
	) {
		jwtService.compareJwtWithPathVar(userId, 1);
		ReadCustomRes response =
				customFacade.updateCustomProcess(
						request.newUpdateCustomReq(userId));
		return BaseResponse.success(SuccessType.UPDATE_CUSTOM_PROCESS_SUCCESS, response);
	}

	@PostMapping(value = "/discounts")
	@Operation(summary = "할인 정책 추가하기")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success"),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> createDiscount(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostDiscountReq request
	) {
		jwtService.compareJwtWithPathVar(userId, 1);
		discountFacade.createDiscount(request.newCreateDiscountReq());
		return BaseResponse.success(SuccessType.CREATE_DISCOUNT_SUCCESS);
	}


	@PostMapping(value = "/stickers/discounts")
	@Operation(summary = "일반 스티커 할인 적용하기")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "success",
					content = @Content(schema = @Schema(implementation = ReadStickerRes.class))),
			@ApiResponse(responseCode = "400, 500", description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> applyStickerDiscount(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid ApplyStickerDiscountReq request
	) {
		jwtService.compareJwtWithPathVar(userId, 1);
		ReadStickerRes response = discountFacade.applyStickerDiscount(request.getStickerId(),
				request.getDiscountId());
		return BaseResponse.success(SuccessType.APPLY_STICKER_DISCOUNT_SUCCESS, response);
	}

//    @GetMapping("/index")
//    public ModelAndView displayArticle(Map<String, Object> model) {
//
//        List<User> users = userProvider.readAllUsers();
//
//        model.put("users", users);
//
//        return new ModelAndView("index", model);
//    }
}