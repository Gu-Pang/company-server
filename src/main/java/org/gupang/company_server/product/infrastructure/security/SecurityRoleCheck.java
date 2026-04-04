package org.gupang.company_server.product.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.gupang.common.entity.UserRole;
import org.gupang.company_server.product.domain.service.RoleCheck;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityRoleCheck implements RoleCheck {

    @Override
    public boolean hasRole(UserRole role) {
        return false;
    }

    @Override
    public boolean hasRole(List<UserRole> roles) {
        return false;
    }

    @Override
    public boolean isMyCompany(UUID companyId) {
        return false;
    }
}