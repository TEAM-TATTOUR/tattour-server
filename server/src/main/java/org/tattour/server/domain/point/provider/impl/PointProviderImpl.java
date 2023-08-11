package org.tattour.server.domain.point.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.dao.PointDao;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointLogListRes;
import org.tattour.server.domain.point.provider.vo.PointLogInfo;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class PointProviderImpl implements PointProvider {

    private final PointDao pointDao;
    private final PointChargeRequestRepositoryImpl pointChargeRequestRepository;

    @Override
    public PointChargeRequest getPointChargeRequestById(Integer id) {
        return pointChargeRequestRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorType.NOT_FOUND_POINT_CHARGE_REQUEST_EXCEPTION));
    }

    @Override
    public ReadPointChargeRequestListRes getPointChargeRequestAfterDate(int userId, String date) {
        return ReadPointChargeRequestListRes.of(
                EntityDtoMapper.INSTANCE.toGetPointChargeRequestResList(
                        pointChargeRequestRepository
                                .findPointChargeRequestByUser_IdAndCreatedAtAfter(userId, date)));
    }

    @Override
    public ReadPointChargeRequestListRes getAllPointChargeRequest(Integer userId,
            Boolean isCompleted) {
        return ReadPointChargeRequestListRes.of(
                pointDao.getPointChargeRequestResList(userId, isCompleted));
    }

    @Override
    public ReadPointLogListRes getPointLog(Integer userId, String title) {
        List<PointLogInfo> userPointLogList = pointDao.getPointLogResList(userId, title);

        return ReadPointLogListRes.of(userPointLogList);
    }
}
