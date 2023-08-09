package org.tattour.server.domain.point.provider.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PointLogInfo {

    private int id;
    private int userId;
    private String title;
    private String content;
    private Integer amount;
    private int resultPointAmount;
    private String createdAt;
    private Boolean state;
}
