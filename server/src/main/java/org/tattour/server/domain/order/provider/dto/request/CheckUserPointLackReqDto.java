package org.tattour.server.domain.order.provider.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckUserPointLackReqDto {
    private Integer userId;
    private Integer totalAmount;

    public static CheckUserPointLackReqDto of(Integer userId, Integer totalAmount) {
        return new CheckUserPointLackReqDto(userId, totalAmount);
    }
}
