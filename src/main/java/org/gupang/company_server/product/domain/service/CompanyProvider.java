package org.gupang.company_server.product.domain.service;

import java.util.UUID;

public interface CompanyProvider {
    CompanyData getCompany(UUID companyId);
}

