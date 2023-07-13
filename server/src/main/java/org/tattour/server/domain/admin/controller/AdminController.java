package org.tattour.server.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API Document")
public class AdminController {
    private final OrderProviderImpl orderProvider;

    // TODO : ADMIN jwt 확인
    @Operation(summary = "모든 결제 내역 불러오기")
    @GetMapping("/order")
    public ResponseEntity<?> getOrderHistory(
            @RequestParam int page
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, orderProvider.getOrderHistory(page));
    }
}
