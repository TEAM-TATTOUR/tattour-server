package org.tattour.server.domain.order.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.order.provider.OrderProvider;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.vo.OrderHistoryPageInfo;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class OrderProviderImpl implements OrderProvider {

    private final OrderRepositoryImpl orderRepository;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;

    @Override
    public Order readOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_ORDER_HISTORY));
    }

    @Override
    public ReadOrderHistoryListRes readOrderHistoryByPage(int page) {
        Page<Order> getOrderHistoryResPage = orderRepository.findAll(
                PageRequest.of(
                        page - 1,
                        10,
                        Sort.by("createdAt")));
        List<OrderHistoryInfo> orderHistoryInfoList =
                EntityDtoMapper.INSTANCE.toGetOrderHistoryListRes(getOrderHistoryResPage);

        return ReadOrderHistoryListRes.of(
                orderHistoryInfoList,
                OrderHistoryPageInfo.of(
                        page,
                        getOrderHistoryResPage.getTotalElements(),
                        getOrderHistoryResPage.getTotalPages()));
    }

    @Override
    public ReadUserOrderHistoryListRes readOrderHistoryByUserId(int userId) {
        List<UserOrderHistoryInfo> userOrderHistoryInfoList =
                EntityDtoMapper.INSTANCE
                        .toGetUserOrderHistoryListRes(orderRepository.findAllByUser_Id(userId));

        return ReadUserOrderHistoryListRes.of(userOrderHistoryInfoList);
    }

    @Override
    public ReadUserOrderHistoryListRes readOrderHistoryAfterDate(int userId, String date) {
        List<UserOrderHistoryInfo> userOrderHistoryInfoList =
                EntityDtoMapper.INSTANCE
                        .toGetUserOrderHistoryListRes(
                                orderRepository.findAllByUser_IdAndCreatedAtAfter(
                                        userId,
                                        date));

        return ReadUserOrderHistoryListRes.of(userOrderHistoryInfoList);
    }

    @Override
    public OrderAmountInfo readOrderAmountRes(int price, int count, int shippingFee) {
        int productAmount = calculateProductAmount(price, count);
        int totalAmount = calculateTotalAmount(productAmount, shippingFee);

        return OrderAmountInfo.of(totalAmount, productAmount, shippingFee);
    }

    @Override
    public int calculateProductAmount(int price, int count) {
        return price * count;
    }

    @Override
    public int calculateTotalAmount(int productAmount, int shippingFee) {
        return productAmount + shippingFee;
    }

}
