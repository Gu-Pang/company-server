package org.gupang.company_server.presentation.dto;

import java.util.UUID;

public record CompanyRequestDto (
        String name,
        String address,
        String addressDetail,
        UUID hubId,
        UUID managerId
){}
