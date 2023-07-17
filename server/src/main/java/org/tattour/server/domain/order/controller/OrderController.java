package org.tattour.server.domain.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.controller.dto.request.GetOrderSheetReq;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.dto.request.CheckUserPointLackReqDto;
import org.tattour.server.domain.order.provider.dto.request.GetOrderSheetReqDto;
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
import org.tattour.server.global.dto.ApiResponse;
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

	@Operation(summary = "결제 페이지 불러오기")
	@GetMapping("/ordersheet")
	public ResponseEntity<?> getOrderSheet(
		@UserId Integer userId,
		@RequestBody @Valid GetOrderSheetReq req
	) {
		return ApiResponse.success(SuccessType.GET_SUCCESS,
			orderProvider.getOrderSheetRes(GetOrderSheetReqDto.of(
				userId,
				req.getStickerId(),
				req.getCount(),
				req.getShippingFee())
			));
	}

	@Operation(summary = "결제하기")
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
			UpdateUserPointReq.of(userId, -Math.abs(req.getTotalAmount())));
		pointService.savePointLog(
			SaveUserPointLogReq.of(
				"상품 구매",
				null,
				-Math.abs(req.getTotalAmount()),
				resultPoint,
				userId));
		discordMessageService.sendOrderStickerMessage(order);
		return ApiResponse.success(SuccessType.CREATE_ORDER_SUCCESS);
	}

	// TODO : pageable로 리팩토링하기
	@Operation(summary = "결제 내역 불러오기")
	@GetMapping
	public ResponseEntity<?> getUserOrderList(
		@Parameter(hidden = true) @UserId Integer jwtUserId,
		@RequestParam("userId") Integer userId
	) {
		jwtService.compareJwtWithPathVar(jwtUserId, userId);

		return ApiResponse.success(SuccessType.GET_SUCCESS,
			orderProvider.getOrderHistoryByUserId(userId));
	}
}
