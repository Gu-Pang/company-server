package org.gupang.company_server.product.infrastructure.provider;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.company.domain.Company;
import org.gupang.company_server.company.domain.CompanyRepository;
import org.gupang.company_server.exception.ErrorCode;
import org.gupang.company_server.product.domain.service.CompanyData;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyProviderImpl implements CompanyProvider {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyData getCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMPANY_NOT_FOUND));

        return company == null ? null : new CompanyData(company.getId(), company.getName());
    }
}