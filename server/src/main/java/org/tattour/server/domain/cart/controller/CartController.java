package org.tattour.server.domain.cart.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.cart.controller.dto.request.CartItemReq;
import org.tattour.server.domain.cart.controller.dto.request.UpdateCartCountReq;
import org.tattour.server.domain.cart.controller.dto.response.CartItemsRes;
import org.tattour.server.domain.cart.facade.CartFacade;
import org.tattour.server.global.config.annotations.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/api/v1/cart")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Cart", description = "Cart API Document")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartFacade cartFacade;

    @Operation(summary = "장바구니 아이템 추가")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "생성에 성공했습니다.",
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
    public ResponseEntity<?> saveCartItem(
            @Parameter(hidden = true) @UserId Integer userId,
            @RequestBody @Valid CartItemReq cartItemReq
    ) {
        cartFacade.saveCartItem(userId, cartItemReq);
        return BaseResponse.success(SuccessType.CREATE_SUCCESS);
    }

    @Operation(summary = "유저 장바구니 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회에 성공했습니다.",
                    content = @Content(schema = @Schema(implementation = CartItemsRes.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "알 수 없는 서버 에러가 발생했습니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    @GetMapping
    public ResponseEntity<?> getUserCartItem(
            @Parameter(hidden = true) @UserId Integer userId
    ) {
        return BaseResponse.success(SuccessType.GET_SUCCESS, cartFacade.getUserCartItems(userId));
    }

    @Operation(summary = "장바구니 수량 일괄 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "갱신에 성공했습니다.",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 장바구니입니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "알 수 없는 서버 에러가 발생했습니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    @PatchMapping
    public ResponseEntity<?> updateCartCount(
            @Parameter(hidden = true) @UserId Integer userId,
            @RequestBody @Valid UpdateCartCountReq req) {
        cartFacade.updateCartsCount(userId, req);
        return BaseResponse.success(SuccessType.UPDATE_SUCCESS);
    }

    @Operation(summary = "장바구니 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "삭제에 성공했습니다.",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 장바구니입니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "알 수 없는 서버 에러가 발생했습니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCartCount(
            @Parameter(hidden = true) @UserId Integer userId,
            @Parameter(description = "장바구니 Id") @PathVariable Integer cartId) {
        cartFacade.deleteCartItem(userId, cartId);
        return BaseResponse.success(SuccessType.DELETE_SUCCESS);
    }
}


