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
import javax.servlet.http.HttpServletRequest;
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
import org.tattour.server.domain.admin.controller.dto.request.AdminLoginReq;
import org.tattour.server.domain.admin.controller.dto.request.ApplyStickerDiscountReq;
import org.tattour.server.domain.admin.controller.dto.request.PatchCustomProcessReq;
import org.tattour.server.domain.admin.controller.dto.request.PostDiscountReq;
import org.tattour.server.domain.admin.controller.dto.request.PostStickerReq;
import org.tattour.server.domain.admin.controller.dto.response.AdminLoginRes;
import org.tattour.server.domain.admin.controller.dto.response.PostStickerRes;
import org.tattour.server.domain.admin.facade.AdminFacade;
import org.tattour.server.domain.custom.facade.CustomFacade;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.discount.facade.DiscountFacade;
import org.tattour.server.domain.order.controller.dto.request.PatchOrderStatusReq;
import org.tattour.server.domain.order.facade.OrderFacade;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.sticker.facade.StickerFacade;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerRes;
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

    private final AdminFacade adminFacade;
    private final OrderFacade orderFacade;
    private final DiscountFacade discountFacade;
    private final StickerFacade stickerFacade;
    private final CustomFacade customFacade;

    @Operation(summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인에 성공했습니다.",
                    content = @Content(schema = @Schema(implementation = AdminLoginRes.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "알 수 없는 서버 에러가 발생했습니다.",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(
            @RequestBody @Valid AdminLoginReq req,
            HttpServletRequest httpServletRequest
    ) {
        return BaseResponse.success(SuccessType.LOGIN_SUCCESS,
                adminFacade.login(req, httpServletRequest.getRemoteAddr()));
    }

    @Operation(summary = "모든 결제내역 불러오기", description = "모든 결제내역 불러오기")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회에 성공했습니다.",
                    content = @Content(schema = @Schema(implementation = ReadOrderHistoryListRes.class))),
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
            @Parameter(description = "페이지 번호", required = true) @RequestParam("page") int page
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
        PostStickerRes response =
                PostStickerRes.of(stickerFacade.createSticker(
                        stickerInfo.newCreateStickerReq(
                                stickerMainImage,
                                stickerImages)));
        return BaseResponse.success(SuccessType.CREATE_SUCCESS, response);
    }

    @PostMapping(value = "/custom/receive/{customId}")
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