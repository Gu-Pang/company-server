package org.gupang.company_server.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.shared.exception.ErrorCode;
import org.gupang.company_server.product.domain.service.CompanyData;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.gupang.company_server.shared.exception.ErrorCode;

import java.util.UUID;

@Getter
@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyInfo {
    @Column(length=36, name="company_id", nullable = false)
    private UUID id;

    @Column(length=80, name="company_name", nullable = false)
    private String name;

    protected CompanyInfo(UUID companyId, CompanyProvider provider) {
        if (companyId == null) {
            throw new CustomException(ErrorCode.COMPANY_ID_REQUIRED);
        }

        if (provider == null) {
            throw new CustomException(ErrorCode.COMPANY_INFO_MISSING);
        }

        CompanyData data = provider.getCompany(companyId);
        if (data == null) {
            throw new CustomException(ErrorCode.COMPANY_NOT_FOUND);
        }

        this.id = data.id();
        this.name = data.name();

    }
}

