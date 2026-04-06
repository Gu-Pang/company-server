package org.gupang.company_server.product.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.gupang.common.entity.UserRole;
import org.gupang.company_server.company.domain.CompanyRepository;
import org.gupang.company_server.product.domain.service.RoleCheck;
import org.gupang.company_server.shared.util.SecurityUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityRoleCheck implements RoleCheck {

    private CompanyRepository companyRepository;

    @Override
    public boolean hasRole(UserRole role) {
        UserDetails userDetails = SecurityUtil.getCurrentUser().orElse(null);

        return userDetails != null &&
                userDetails.getAuthorities() != null &&
                userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role.name()));

    }

    @Override
    public boolean hasRole(List<UserRole> roles) {
        return roles != null && roles.stream().anyMatch(this::hasRole);
    }

    @Override
    public boolean isMyCompany(UUID companyId) {

        // companyId + ManagerId로 조회된 레코드가 있으면 true
        String userId = SecurityUtil.getCurrentUserId().orElse(null);

        return StringUtils.hasText(userId) && companyId != null
                && companyRepository.existsByIdAndManagerId(companyId, UUID.fromString(userId));
    }

}