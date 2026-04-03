package org.gupang.company_server.product.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceDto {

    @Getter
    @Builder
    public static class Create {
        private String name;
        private int stock;
        private long price;
        private UUID companyId;
    }
}