package org.tattour.server.domain.order.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.order.domain.OrderHistory;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.OrderProvider;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class OrderProviderImpl implements OrderProvider {

    private final OrderRepositoryImpl orderRepository;

    @Override
    public OrderHistory readOrderHistoryById(int orderHistoryId) {
        return orderRepository.findById(orderHistoryId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_ORDER_HISTORY));
    }

    @Override
    public Page<OrderHistory> readOrderHistoryByPage(int page) {
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
}
