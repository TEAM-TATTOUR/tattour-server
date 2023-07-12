package org.tattour.server.domain.order.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.order.controller.dto.request.GetOrderSheetReq;
import org.tattour.server.domain.order.controller.dto.response.GetOrderSheetRes;
import org.tattour.server.domain.order.provider.OrderProvider;
import org.tattour.server.domain.order.provider.dto.response.GetOrderAmountRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderPointRes;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.sticker.provider.dto.response.GetOrderSheetStickerInfo;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;

@Service
@RequiredArgsConstructor
public class OrderProviderImpl implements OrderProvider {
    private final OrderRepositoryImpl orderRepository;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;

    @Override
    public GetOrderSheetRes getOrderSheetRes(GetOrderSheetReq req) {
        // 스티커 정보(배너이미지, 이름, 원래가격, 할인가격) + 개수
        GetOrderSheetStickerInfo getOrderSheetStickerInfo = stickerProvider.getOrderSheetStickerInfo(req.getStickerId());
        getOrderSheetStickerInfo.setCount(req.getCount());

        // 결제 금액 정보
        // 총 결제 금액, 총 상품 금액, 배송비
        int totalAmount = getOrderSheetStickerInfo.getPrice() * req.getCount() + req.getShippingFee();
        int productAmount = getOrderSheetStickerInfo.getPrice() * req.getCount();
        GetOrderAmountRes getOrderAmountRes = GetOrderAmountRes.of(
                totalAmount,
                productAmount,
                req.getShippingFee());

        // 포인트
        // 보유 포인트, 남는 포인트
        int userPoint = userProvider.getUserById(req.getUserId()).getPoint();
        int restPoint = userPoint - totalAmount;
        boolean isLacked = false;
        int resultPoint;

        if(restPoint > 0) {
            resultPoint = restPoint;
        } else {
            isLacked = true;
            resultPoint = Math.abs(restPoint);
        }
        GetUserOrderPointRes getUserOrderPointRes = GetUserOrderPointRes.of(userPoint, resultPoint, isLacked);

        return GetOrderSheetRes.of(getOrderSheetStickerInfo, getOrderAmountRes, getUserOrderPointRes);
    }

    @Override
    public GetUserOrderHistoryListRes getUserOrderHistory(Integer userId) {
        return null;
    }
}
