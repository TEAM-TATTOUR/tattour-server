package org.tattour.server.domain.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderRequest;
import org.tattour.server.domain.order.facade.impl.OrderFacadeImpl;
import org.tattour.server.domain.order.facade.dto.request.ReadOrderSheetReq;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.global.config.annotations.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Order", description = "Order API Document")
public class OrderController {

	private final OrderFacadeImpl orderFacade;

	@Operation(summary = "결제 페이지 불러오기", description = "제품 상세 페이지에서 받은 정보를 바탕으로 결제 시트 정보 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ReadOrderSheetRes.class))),
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
				orderFacade.readOrderSheet(
						ReadOrderSheetReq.of(
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
		orderFacade.createOrder(CreateOrderRequest.of(
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

		return BaseResponse.success(SuccessType.CREATE_ORDER_SUCCESS);
	}


	// TODO : pageable로 리팩토링하기
	@Operation(summary = "유저 결제 내역 불러오기", description = "유저 id로 결제 내역 불러오기")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "조회에 성공했습니다.",
					content = @Content(schema = @Schema(implementation = ReadUserOrderHistoryListRes.class))),
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
	@GetMapping("/history")
	public ResponseEntity<?> getUserOrderList(
			@Parameter(hidden = true) @UserId Integer userId
	) {
		return BaseResponse.success(SuccessType.GET_SUCCESS,
				orderFacade.readOrderHistoryByUserId(userId));
	}
}
