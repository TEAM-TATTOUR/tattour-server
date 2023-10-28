package org.tattour.server.domain.admin.service;

import org.tattour.server.domain.admin.domain.Admin;

public interface AdminService {

    Admin readAdminByAdminName(String adminName);

    boolean compareCredentials(Admin user, String userName, String password);
    
}
