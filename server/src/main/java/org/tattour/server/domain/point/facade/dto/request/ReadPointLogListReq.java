package org.tattour.server.domain.point.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.point.domain.PointLogCategory;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadPointLogListReq {
    Integer userId;
    PointLogCategory category;

    public static ReadPointLogListReq of(Integer userId, PointLogCategory category){
        return new ReadPointLogListReq(userId, category);
    }
}
