package org.tattour.server.domain.admin.facade;

import org.tattour.server.domain.admin.controller.dto.request.AdminLoginReq;
import org.tattour.server.domain.admin.controller.dto.response.AdminLoginRes;

public interface AdminFacade {

    AdminLoginRes login(AdminLoginReq req, String accessIp);
}
