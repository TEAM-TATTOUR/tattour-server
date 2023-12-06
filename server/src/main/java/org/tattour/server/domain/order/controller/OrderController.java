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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.controller.dto.request.OrderReq;
import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.domain.PurchaseRequest;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderReq;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.facade.impl.OrderFacadeImpl;
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
@Validated
public class OrderController {

    private final OrderFacadeImpl orderFacade;

    // todo: 장바구니 목록이 존재하지 않을 경우 예외처리하기
    @Operation(summary = "결제 페이지 불러오기",
            description = "결제 페이지 정보 불러오기"
                    + "\n- stickerId, count가 존재하는 경우: **단일 상품 구매**"
                    + "\n- stickerId, count가 존재하지 않는 경우: **장바구니 상품 구매**")
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
            @Parameter(description = "타투 스티커 id") @RequestParam(required = false) Integer stickerId,
            @Parameter(description = "상품 개수") @RequestParam(required = false) Integer count
    ) {
        return BaseResponse.success(
                SuccessType.GET_SUCCESS, orderFacade.readOrderSheet(userId, PurchaseRequest.of(stickerId, count)));
    }


    @Operation(summary = "결제하기",
            description = "결제 페이지에서 결제하기"
                    + "\n- stickerId, count가 존재하는 경우: **단일 상품 구매**"
                    + "\n- stickerId, count가 존재하지 않는 경우: **장바구니 상품 구매**")
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
            @RequestBody @Valid OrderReq orderReq,
            @Parameter(description = "타투 스티커 id") @RequestParam(required = false) Integer stickerId,
            @Parameter(description = "상품 개수") @RequestParam(required = false) Integer count
    ) {
        orderFacade.order(
                PurchaseRequest.of(stickerId, count),
                CreateOrderReq.builder()
                        .userId(userId)
                        .productAmount(orderReq.getProductAmount())
                        .shippingFee(orderReq.getShippingFee())
                        .totalAmount(orderReq.getTotalAmount())
                        .recipientName(orderReq.getRecipientName())
                        .contact(orderReq.getContact())
                        .mailingAddress(orderReq.getMailingAddress())
                        .baseAddress(orderReq.getBaseAddress())
                        .detailAddress(orderReq.getDetailAddress())
                        .build());

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
