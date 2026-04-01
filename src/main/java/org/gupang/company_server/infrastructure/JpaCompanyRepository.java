package org.gupang.company_server.infrastructure;

import org.gupang.company_server.domain.company.model.Company;
import org.gupang.company_server.domain.company.repository.CompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCompanyRepository extends JpaRepository<Company, UUID>, CompanyRepository {
}
