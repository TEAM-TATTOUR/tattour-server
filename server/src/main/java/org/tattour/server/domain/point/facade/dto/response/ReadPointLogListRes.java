package org.tattour.server.domain.point.facade.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.point.provider.vo.PointLogInfo;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadPointLogListRes {

    List<PointLogInfo> pointLogInfoList;

    public static ReadPointLogListRes of(List<PointLogInfo> pointLogInfoList) {
        return new ReadPointLogListRes(pointLogInfoList);
    }
}
