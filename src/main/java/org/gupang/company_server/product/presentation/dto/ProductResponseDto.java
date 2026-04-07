package org.gupang.company_server.product.presentation.dto;

import org.gupang.company_server.product.domain.Product;

import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        int stock,
        long price,
        UUID companyId
) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getStock(),
                product.getPrice(),
                product.getCompany().getId()
        );
    }
}
