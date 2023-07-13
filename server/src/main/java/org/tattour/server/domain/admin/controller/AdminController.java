package org.tattour.server.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API Document")
public class AdminController {
    private final OrderProviderImpl orderProvider;
    private final PointProviderImpl pointProvider;

    // TODO : ADMIN jwt 확인
    @Operation(summary = "모든 결제 내역 불러오기")
    @GetMapping("/order")
    public ResponseEntity<?> getOrderHistory(
            @RequestParam("page") int page
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, orderProvider.getOrderHistory(page));
    }

    @Operation(summary = "포인트 충전 신청 내역 불러오기")
    @GetMapping("/point/requests")
    public ResponseEntity<?> getPointChargeRequest(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Boolean isComplete
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, pointProvider.getAllPointChargeRequest(userId, isComplete));
    }
}
