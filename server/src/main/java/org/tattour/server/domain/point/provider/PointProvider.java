package org.tattour.server.domain.point.provider;

import java.util.List;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.provider.vo.PointLogInfo;
import org.tattour.server.domain.user.domain.User;

public interface PointProvider {

    // id로 포인트 충전 요청 가져오기
    PointChargeRequest readPointChargeRequestById(Integer id);

    // id와 날짜로 포인트 충전 요청 가져오기
    ReadPointChargeRequestListRes readPointChargeRequestAfterDate(int userId, String date);

    // 조건에 따라 포인트 충전 요청 가져오기
    ReadPointChargeRequestListRes readAllPointChargeRequest(Integer userId, Boolean isCompleted);

    // 조건에 따라 포인트 로그 가져오기
    List<PointLogInfo> readPointLog(Integer userId, String category);
}
