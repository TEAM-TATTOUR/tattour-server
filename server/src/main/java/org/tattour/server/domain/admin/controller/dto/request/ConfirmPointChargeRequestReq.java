package org.tattour.server.domain.admin.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConfirmPointChargeRequestReq {
    private int id;
    private int userId;
    private int transferredAmount;
}
