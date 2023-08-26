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
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class OrderProviderImpl implements OrderProvider {

    private final OrderRepositoryImpl orderRepository;

    @Override
    public Order readOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_ORDER_HISTORY));
    }

    @Override
    public Page<Order> readOrderHistoryByPage(int page) {
        return orderRepository.findAll(
                PageRequest.of(
                        page - 1,
                        10,
                        Sort.by("createdAt")));
    }

    @Override
    public ReadUserOrderHistoryListRes readOrderHistoryByUserId(int userId) {
        List<UserOrderHistoryInfo> userOrderHistoryInfoList =
                EntityDtoMapper.INSTANCE
                        .toGetUserOrderHistoryListRes(orderRepository.findAllByUser_Id(userId));

        return ReadUserOrderHistoryListRes.of(userOrderHistoryInfoList);
    }

    @Override
    public List<UserOrderHistoryInfo> readOrderHistoryAfterDate(int userId, String date) {
        return EntityDtoMapper.INSTANCE
                        .toGetUserOrderHistoryListRes(
                                orderRepository.findAllByUser_IdAndCreatedAtAfter(
                                        userId,
                                        date));
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
