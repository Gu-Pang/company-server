package org.gupang.company_server.product.presentation.dto;

import java.util.UUID;

public record ProductRequestDto(
        String name,
        int stock,
        long price,
        UUID companyId
) {

}
