package org.gupang.company_server.company.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyServiceDto {

    @Getter
    @Builder
    public static class Create {
        private String name;
        private String address;
        private String addressDetail;
        private UUID hubId;
        private UUID managerId;
    }

    @Getter
    @Builder
    public static class Update {
        private String name;
        private String address;
        private String addressDetail;
    }

}
