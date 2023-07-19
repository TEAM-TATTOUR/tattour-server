package org.tattour.server.domain.point.service;

import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestDto;
import org.tattour.server.domain.point.service.dto.request.PatchPointChangeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.dto.response.ConfirmPointChargeResponseDto;

public interface PointService {

    // 포인트 충전 요청 저장하기
    void savePointChargeRequest(SavePointChargeRequestReq req);

    // 포인트 로그 저장하기
    void savePointLog(SaveUserPointLogReq req);

    // 포인트 충전 요청 수정하기
    void updatePointChargeRequest(PatchPointChangeRequestReq req);

    // 포인트 충전 요청 상태 검증
    ConfirmPointChargeResponseDto confirmPointChargeRequest(ConfirmPointChargeRequestDto req);

    // 포인트 충전 요청 취소
    void cancelPointChargeRequest(CancelPointChargeRequestReq req);
}
