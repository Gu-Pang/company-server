package org.gupang.company_server.company.infrastructure;

import org.gupang.company_server.company.domain.Company;
import org.gupang.company_server.company.domain.CompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCompanyRepository extends JpaRepository<Company, UUID>, CompanyRepository {
}
