package org.tattour.server.domain.order.facade.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.facade.OrderFacade;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderRequest;
import org.tattour.server.domain.order.facade.dto.request.ReadOrderSheetReq;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.user.provider.vo.UserPointAfterOrderInfo;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderProviderImpl orderProvider;
    private final OrderServiceImpl orderService;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;
    private final UserServiceImpl userService;
    private final PointServiceImpl pointService;
    private final DiscordMessageService discordMessageService;

    @Override
    @Transactional
    public ReadOrderSheetRes readOrderSheet(ReadOrderSheetReq req) {
        // 스티커 정보(배너이미지, 이름, 원래가격, 할인가격) + 개수
        ReadOrderSheetStickerInfo readOrderSheetStickerInfo =
                stickerProvider.readOrderSheetStickerInfo(req.getStickerId());
        readOrderSheetStickerInfo.setCount(req.getCount());

        // 결제 금액 정보
        // 총 결제 금액, 총 상품 금액, 배송비
        OrderAmountInfo orderAmountInfo =
                orderProvider.readOrderAmountRes(
                        Objects.isNull(readOrderSheetStickerInfo.getDiscountedPrice())
                                ? readOrderSheetStickerInfo.getPrice()
                                : readOrderSheetStickerInfo.getDiscountedPrice(),
                        readOrderSheetStickerInfo.getCount(),
                        req.getShippingFee());

        // 포인트
        // 보유 포인트, 남는 포인트
        UserPointAfterOrderInfo userPointAfterOrderInfo =
                userProvider.readUserPointAfterOrderInfo(
                        req.getUserId(),
                        orderAmountInfo.getTotalAmount());

        return ReadOrderSheetRes.of(readOrderSheetStickerInfo, orderAmountInfo,
                userPointAfterOrderInfo);
    }

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest req) {
        if (userProvider.isUserPointLack(req.getUserId(), req.getTotalAmount())) {
            throw new BusinessException(ErrorType.LACK_OF_POINT_EXCEPTION);
        }

        // 주문내역 생성
        Order order = orderService.saveOrder(req);

        // userPoint 수정
        int resultPoint = userService.updateUserPoint(req.getUserId(), -req.getTotalAmount());

        // 포인트 로그 저장
        pointService.savePointLog(
                "상품 구매",
                null,
                -req.getTotalAmount(),
                resultPoint,
                req.getUserId());

        discordMessageService.sendOrderStickerMessage(order);
    }

    @Override
    @Transactional
    public ReadUserOrderHistoryListRes readOrderHistoryByUserId(Integer userId) {
        return orderProvider.readOrderHistoryByUserId(userId);
    }
}
