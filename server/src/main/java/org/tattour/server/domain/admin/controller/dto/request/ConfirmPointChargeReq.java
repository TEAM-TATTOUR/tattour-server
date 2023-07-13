package org.tattour.server.domain.admin.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConfirmPointChargeReq {
    private int id;
    private boolean isApproved;
}
