package org.gupang.company_server.product.presentation.dto;

import org.gupang.company_server.product.domain.Product;

import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        int stock,
        long price,
        UUID companyId,
        String companyName
) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getPrice(),
                product.getCompany().getId(),
                product.getCompany().getName()
        );
    }
}
