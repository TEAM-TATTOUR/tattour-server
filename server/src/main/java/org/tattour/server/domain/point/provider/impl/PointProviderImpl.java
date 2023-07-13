package org.tattour.server.domain.point.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.dao.PointDao;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.provider.dto.request.GetPointChargeRequestAfterDate;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
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
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_POINT_CHARGE_REQUEST_EXCEPTION));
    }

    @Override
    public GetPointChargeRequestListRes getPointChargeRequestAfterDate(
            GetPointChargeRequestAfterDate req) {
        return GetPointChargeRequestListRes.of(
                EntityDtoMapper.INSTANCE.toGetPointChargeRequestResList(
                        pointChargeRequestRepository
                                .findPointChargeRequestByUser_IdAndCreatedAtAfter(req.getUserId(), req.getDate())));
    }

    @Override
    public GetPointChargeRequestListRes getAllPointChargeRequest(Integer userId, Boolean isCompleted) {
        return GetPointChargeRequestListRes.of(pointDao.getPointChargeRequestResList(userId, isCompleted));
    }
}
