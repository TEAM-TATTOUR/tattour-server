package org.tattour.server.domain.admin.service;

import org.tattour.server.domain.admin.domain.Admin;

public interface AdminService {

    void saveAdmin(Admin admin);

    Admin readAdminByAdminName(String adminName);

    boolean compareCredentials(Admin admin, String adminName, String password);

    boolean isAccountLocked(Admin admin);

    void addLoginFailCnt(Admin admin);
}
