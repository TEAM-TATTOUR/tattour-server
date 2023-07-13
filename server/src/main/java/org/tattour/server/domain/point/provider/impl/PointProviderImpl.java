package org.tattour.server.domain.point.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.dao.PointDao;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;

@Service
@RequiredArgsConstructor
public class PointProviderImpl implements PointProvider {
    private final PointDao pointDao;
    @Override
    public GetPointChargeRequestListRes getAllPointChargeRequest(Integer userId, Boolean isCompleted) {
        return GetPointChargeRequestListRes.of(pointDao.getPointChargeRequestResList(userId, isCompleted));
    }
}
