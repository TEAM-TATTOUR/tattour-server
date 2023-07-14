package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserPointLogReq {
    private String title;
    private String content;
    private Integer amount;
    private Integer resultPoint;
    private Integer userId;


    public static SaveUserPointLogReq of(String title, String content, Integer amount, Integer resultPoint, Integer userId) {
        return new SaveUserPointLogReq(title, content, amount, resultPoint, userId);
    }
}
