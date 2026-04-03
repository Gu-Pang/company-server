package org.gupang.company_server.product.domain.service;

import java.util.UUID;

public record CompanyData(
        UUID id,
        String name
) {}