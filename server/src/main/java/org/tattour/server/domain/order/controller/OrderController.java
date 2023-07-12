package org.tattour.server.domain.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.order.controller.dto.request.GetOrderSheetReq;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProviderImpl orderProvider;
    private final OrderServiceImpl orderService;
    private final JwtService jwtService;

    @Operation(summary = "결제 페이지 불러오기")
    @GetMapping("/ordersheet")
    public ResponseEntity<?> getOrderSheet(
            @RequestBody GetOrderSheetReq req
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, orderProvider.getOrderSheetRes(req));
    }
    @Operation(summary = "결제하기")
    @PostMapping
    public ResponseEntity<?> order (
            @UserId Integer jwtUserId,
            @RequestBody PostOrderReq req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, req.getUserId());
        orderService.saveOrder(req);

        return ApiResponse.success(SuccessType.GET_SUCCESS);
    }
}
