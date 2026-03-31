package org.gupang.company_server.domain.company.repository;

import org.gupang.company_server.domain.company.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
    Company save(Company company);
    Optional<Company> findById(UUID id);
    Page<Company> findAllByIsDeletedFalse(Pageable pageable);
}
