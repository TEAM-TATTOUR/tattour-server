package org.tattour.server.domain.admin.service;

import org.tattour.server.domain.admin.model.Admin;

public interface AdminService {

    void saveAdmin(Admin admin);

    Admin readAdminByAdminName(String adminName);

    boolean validateCredentials(Admin admin, String adminName, String password);

    boolean isAccountLocked(Admin admin);

    void addLoginFailCnt(Admin admin);

    void saveAdminAccessLog(Admin admin, String accessIp);
}
