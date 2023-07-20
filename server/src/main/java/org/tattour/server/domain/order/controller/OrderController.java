package org.tattour.server.domain.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.controller.dto.response.GetOrderSheetRes;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.dto.request.CheckUserPointLackReqDto;
import org.tattour.server.domain.order.provider.dto.request.GetOrderSheetReqDto;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.service.dto.request.PostOrderReqDto;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order API Document")
public class OrderController {

	private final OrderProviderImpl orderProvider;
	private final OrderServiceImpl orderService;
	private final PointServiceImpl pointService;
	private final UserProviderImpl userProvider;
	private final UserServiceImpl userService;
	private final DiscordMessageService discordMessageService;
	private final JwtService jwtService;

	@Operation(summary = "결제 페이지 불러오기", description = "제품 상세 페이지에서 받은 정보를 바탕으로 결제 시트 정보 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = GetOrderSheetRes.class))),
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
					description = "존재하지 않는 스티커입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/ordersheet")
	public ResponseEntity<?> getOrderSheet(
			@Parameter(hidden = true) @UserId Integer userId,
			@Parameter(description = "타투 스티커 id") @RequestParam @NotNull(message = "stickerId is null") Integer stickerId,
			@Parameter(description = "상품 개수", example = "3") @RequestParam @NotNull(message = "count is null") Integer count,
			@Parameter(description = "배송비", example = "3000") @RequestParam @NotNull(message = "shippingFee is null") Integer shippingFee
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				orderProvider.getOrderSheetRes(
						GetOrderSheetReqDto.of(
								userId,
								stickerId,
								count,
								shippingFee)));
	}


	@Operation(summary = "결제하기", description = "결제 시트 페이지에서 결제하기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "주문에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "403",
					description = "포인트가 부족하여 결제할 수 없습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 유저입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "404",
					description = "존재하지 않는 스티커입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping
	public ResponseEntity<?> order(
			@Parameter(hidden = true) @UserId Integer userId,
			@RequestBody @Valid PostOrderReq req
	) {
		if (userProvider.isUserPointLack(
				CheckUserPointLackReqDto.of(userId, req.getTotalAmount()))) {
			throw new BusinessException(ErrorType.LACK_OF_POINT_EXCEPTION);
		}

		Order order = orderService.saveOrder(PostOrderReqDto.of(
				userId,
				req.getStickerId(),
				req.getProductCount(),
				req.getShippingFee(),
				req.getTotalAmount(),
				req.getRecipientName(),
				req.getContact(),
				req.getMailingAddress(),
				req.getBaseAddress(),
				req.getDetailAddress()));
		int resultPoint = userService.updateUserPoint(
				UpdateUserPointReq.of(userId, -req.getTotalAmount()));
		pointService.savePointLog(
				SaveUserPointLogReq.of(
						"상품 구매",
						null,
						-req.getTotalAmount(),
						resultPoint,
						userId));
		discordMessageService.sendOrderStickerMessage(order);

		return BaseResponse.success(SuccessType.CREATE_ORDER_SUCCESS);
	}


	// TODO : pageable로 리팩토링하기
	@Operation(summary = "유저 결제 내역 불러오기", description = "유저 id로 결제 내역 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = GetUserOrderHistoryListRes.class))),
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
					description = "존재하지 않는 스티커입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping
	public ResponseEntity<?> getUserOrderList(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				orderProvider.getOrderHistoryByUserId(userId));
	}
}
