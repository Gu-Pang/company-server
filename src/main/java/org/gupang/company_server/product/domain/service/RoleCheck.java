package org.gupang.company_server.product.domain.service;

import org.gupang.common.entity.UserRole;

import java.util.List;
import java.util.UUID;

public interface RoleCheck {
    boolean hasRole(UserRole role);
    boolean hasRole(List<UserRole> roles);
    boolean isMyCompany(UUID companyId);
}
