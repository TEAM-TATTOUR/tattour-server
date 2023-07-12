package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserPointLogReq {
    private String content;
    private Integer amount;
    private Integer userId;

    public static SaveUserPointLogReq of(String content, Integer amount, Integer userId) {
        return new SaveUserPointLogReq(content, amount, userId);
    }
}
