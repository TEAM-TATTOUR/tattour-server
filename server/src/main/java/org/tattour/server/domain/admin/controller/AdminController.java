package org.tattour.server.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.admin.controller.dto.request.ConfirmPointChargeRequestReq;
import org.tattour.server.domain.order.controller.dto.request.PatchOrderStatusReq;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.point.provider.dto.request.GetPointLogListReq;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestDto;
import org.tattour.server.domain.order.service.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.point.service.dto.response.ConfirmPointChargeResponseDto;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
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
    private final OrderServiceImpl orderService;

    // TODO : ADMIN jwt 확인
    @Operation(summary = "모든 결제 내역 불러오기")
    @GetMapping("/order")
    public ResponseEntity<?> getOrderHistory(
            @RequestParam("page") int page
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, orderProvider.getOrderHistoryByPage(page));
    }

    @Operation(summary = "포인트 충전 신청 내역 불러오기")
    @GetMapping("/point/request")
    public ResponseEntity<?> getPointChargeRequest(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Boolean isCompleted
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, pointProvider.getAllPointChargeRequest(userId, isCompleted));
    }

    @Operation(summary = "포인트 충전 요청 검증")
    @PostMapping("/point/request/confirm")
    public ResponseEntity<?> confirmPointChargeRequest(
            @RequestBody ConfirmPointChargeRequestReq req
    ){
        ConfirmPointChargeResponseDto response = pointService.confirmPointChargeRequest(
                ConfirmPointChargeRequestDto.of(req.getId(), req.getUserId(), req.getTransferredAmount()));
        if(Objects.isNull(response))
            return ApiResponse.success(SuccessType.POINT_CHARGE_CONFIRM_SUCCESS);
        else
            return ApiResponse.success(SuccessType.POINT_CHARGE_CONFIRM_FAIL, response);
    }

    @Operation(summary = "포인트 충전 요청 취소")
    @PostMapping("/point/request/cancel")
    public ResponseEntity<?> cancelPointChargeRequest(
            @RequestBody CancelPointChargeRequestReq req
    ){
        pointService.cancelPointChargeRequest(req);
//                CancelPointChargeRequestReq.of(req.getId(), req.getUserId(), req.getTransferredAmount(), req.getReason()));
        return ApiResponse.success(SuccessType.POINT_CHARGE_CANCEL_SUCCESS);
    }

    @Operation(summary = "포인트 로그 불러오기")
    @GetMapping("/pointLog")
    public ResponseEntity<?> getPointLog(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String title
    ){
        return ApiResponse.success(
                SuccessType.READ_POINT_LOG_SUCCESS,
                pointProvider.getPointLog(GetPointLogListReq.of(userId, title)));
    }

    @Operation(summary = "결제 내역 주문상태 변경")
    @PatchMapping("/order/{orderId}/status")
    public ResponseEntity<?> patchOrderStatus(
            @PathVariable int orderId,
            @RequestBody PatchOrderStatusReq req
    ){
        orderService.updateOrderStatus(UpdateOrderStatusReq.of(orderId, req.getOrderStatus()));

        return ApiResponse.success(SuccessType.UPDATE_ORDER_STATUS_SUCCESS);
    }
}
