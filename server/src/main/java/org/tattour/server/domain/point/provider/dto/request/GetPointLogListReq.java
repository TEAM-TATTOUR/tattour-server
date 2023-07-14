package org.tattour.server.domain.point.provider.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointLogListReq {
    public Integer userId;
    public String title;

    public static GetPointLogListReq of(Integer userId, String title){
        return new GetPointLogListReq(userId, title);
    }
}
