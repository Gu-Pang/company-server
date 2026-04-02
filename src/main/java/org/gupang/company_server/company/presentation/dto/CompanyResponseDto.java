package org.gupang.company_server.company.presentation.dto;

import org.gupang.company_server.company.domain.Company;

import java.util.UUID;

public record CompanyResponseDto(UUID id, String name ,String address, String addressDetail, UUID hubId, UUID managerId) {
    public static CompanyResponseDto from(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getAddressDetail(),
                company.getHubId(),
                company.getManagerId());
    }
}
