package org.tattour.server.domain.order.provider.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserOrderPointRes {
    private int userPoint;
    private int resultPoint;
    private boolean isLacked;

    public static GetUserOrderPointRes of(int userPoint, int resultPoint, boolean isLacked){
        return new GetUserOrderPointRes(userPoint, resultPoint, isLacked);
    }
}
