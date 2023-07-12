package org.tattour.server.domain.point.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
import org.tattour.server.domain.point.provider.dto.response.GetUserPointChargeRequestListRes;
@Service
@RequiredArgsConstructor
public class PointProviderImpl implements PointProvider {

    @Override
    public GetPointChargeRequestListRes getAllPointChargeRequest() {
        return null;
    }

    @Override
    public GetUserPointChargeRequestListRes getUserPointChargeRequest() {
        return null;
    }
}
