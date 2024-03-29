package org.tattour.server.domain.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.admin.model.Admin;
import org.tattour.server.domain.admin.model.AdminAccessLog;
import org.tattour.server.domain.admin.repository.AdminAccessLogRepository;
import org.tattour.server.domain.admin.repository.AdminRepositoryImpl;
import org.tattour.server.domain.admin.service.AdminService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepositoryImpl adminRepository;
    private final AdminAccessLogRepository adminAccessLogRepository;

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin readAdminByAdminName(String adminName) {
        return adminRepository.findByAdminName(adminName)
                .orElseThrow(
                        () -> new BusinessException(ErrorType.AUTHENTICATION_FAILED_EXCEPTION));
    }

    @Override
    public boolean validateCredentials(Admin admin, String adminName, String password) {
        return admin.getAdminName().equals(adminName)
                && passwordEncoder.matches(password, admin.getPassword());
    }

    @Override
    public boolean isAccountLocked(Admin admin) {
        return admin.getLoginFailCnt() == 5;
    }

    @Override
    public void addLoginFailCnt(Admin admin) {
        admin.addLoginFailCnt();
        saveAdmin(admin);
    }

    @Override
    public void saveAdminAccessLog(Admin admin, String accessIp) {
        adminAccessLogRepository.save(AdminAccessLog.of(admin, accessIp));
    }
}
