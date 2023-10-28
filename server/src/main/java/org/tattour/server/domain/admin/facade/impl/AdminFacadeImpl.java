package org.tattour.server.domain.admin.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.admin.controller.dto.request.AdminLoginReq;
import org.tattour.server.domain.admin.controller.dto.response.AdminLoginRes;
import org.tattour.server.domain.admin.domain.Admin;
import org.tattour.server.domain.admin.facade.AdminFacade;
import org.tattour.server.domain.admin.service.AdminService;
import org.tattour.server.domain.user.domain.UserRole;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class AdminFacadeImpl implements AdminFacade {

    private final JwtService jwtService;
    private final AdminService adminService;

    @Override
    @Transactional
    public AdminLoginRes login(AdminLoginReq req, String accessIp) {
        Admin admin = adminService.readAdminByAdminName(req.getUserName());

        if (adminService.isAccountLocked(admin)) {
            throw new BusinessException(ErrorType.ACCOUNT_LOCKED_EXCEPTION);
        }

        if (!adminService.validateCredentials(admin, req.getUserName(), req.getPassword())) {
            adminService.addLoginFailCnt(admin);
            throw new BusinessException(ErrorType.AUTHENTICATION_FAILED_EXCEPTION);
        }

        adminService.saveAdminAccessLog(admin, accessIp);
        return AdminLoginRes.of(
                jwtService.issuedToken(admin.getId(), UserRole.ADMIN.toString()));
    }
}
