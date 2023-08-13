package org.tattour.server.domain.point.facade;

import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.point.facade.dto.request.CreatePointChargeRequestReq;
import org.tattour.server.domain.point.facade.dto.request.ReadPointChargeRequestListReq;
import org.tattour.server.domain.point.facade.dto.request.ReadPointLogListReq;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointLogListRes;
import org.tattour.server.domain.point.facade.dto.request.ConfirmPointChargeReq;
import org.tattour.server.domain.point.facade.dto.response.ConfirmPointChargeRes;

public interface PointFacade {
    // 포인트 충전 요청
    void createPointChargeRequest(CreatePointChargeRequestReq req);

    // 포인트 충전 요청 신청 내역 가져오기
    ReadPointChargeRequestListRes readPointChargeRequest(ReadPointChargeRequestListReq req);

    // 포인트 충전 요청 확인
    ConfirmPointChargeRes confirmPointChargeRequest(ConfirmPointChargeReq req);

    // 포인트 충전 요청 취소
    void cancelPointChargeRequest(CancelPointChargeRequestReq req);

    // 포인트 로그 불러오기
    ReadPointLogListRes readPointLog(ReadPointLogListReq req);
}
