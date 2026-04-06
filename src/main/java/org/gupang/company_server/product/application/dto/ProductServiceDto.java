package org.gupang.company_server.product.application.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
public class ProductServiceDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String name;
        private int stock;
        private long price;
        private UUID companyId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String name;
        private int stock;
        private long price;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStock {
        private int amount;
    }
}