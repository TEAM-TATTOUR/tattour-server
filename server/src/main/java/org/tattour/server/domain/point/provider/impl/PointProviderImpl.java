package org.tattour.server.domain.point.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.dao.PointDao;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.PointLogCategory;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
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
    public PointChargeRequest readPointChargeRequestById(Integer id) {
        return pointChargeRequestRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorType.NOT_FOUND_POINT_CHARGE_REQUEST_EXCEPTION));
    }

    @Override
    public ReadPointChargeRequestListRes readPointChargeRequestAfterDate(int userId, String date) {
        return ReadPointChargeRequestListRes.of(
                EntityDtoMapper.INSTANCE.toGetPointChargeRequestResList(
                        pointChargeRequestRepository
                                .findPointChargeRequestByUser_IdAndCreatedAtAfter(userId, date)));
    }

    @Override
    public ReadPointChargeRequestListRes readAllPointChargeRequest(Integer userId,
            Boolean isCompleted) {
        return ReadPointChargeRequestListRes.of(
                pointDao.findPointChargeRequestResList(userId, isCompleted));
    }

    @Override
    public List<PointLogInfo> readPointLog(Integer userId, String category) {
        return pointDao.findPointLogResList(userId, category);
    }
}
