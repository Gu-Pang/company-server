package org.gupang.company_server.company.presentation;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.gupang.company_server.company.application.CompanyService;
import org.gupang.company_server.company.application.dto.CompanyServiceDto;
import org.gupang.company_server.company.presentation.dto.CompanyRequestDto;
import org.gupang.company_server.company.presentation.dto.CompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class ComponyController {
    private final CompanyService companyService;

    // 업체 생성
    @PostMapping
    public ResponseEntity<UUID> createCompany(@RequestBody CompanyRequestDto requestDto){
        CompanyServiceDto.Create create = CompanyServiceDto.Create.builder()
                .name(requestDto.name())
                .address(requestDto.address())
                .addressDetail(requestDto.addressDetail())
                .hubId(requestDto.hubId())
                .managerId(requestDto.managerId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(create));
    }

    // 업체 수정
    @PatchMapping("/{company_id}")
    public ResponseEntity<Void> upateCompany(@PathVariable("company_id") UUID id, @RequestBody CompanyRequestDto requestDto){
        CompanyServiceDto.Update update = CompanyServiceDto.Update.builder()
                        .name(requestDto.name())
                        .address(requestDto.address())
                        .addressDetail(requestDto.addressDetail())
                        .build();
        companyService.updateCompany(id, update);
        return ResponseEntity.noContent().build();
    }

    // 업체 전체 조회
    @GetMapping
    public ResponseEntity<Page<CompanyResponseDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(companyService.getCompanies(pageable));
    }

    // 업체 상세 조회
    @GetMapping("/{company_id}")
    public ResponseEntity<CompanyResponseDto> getOne(@PathVariable("company_id") UUID id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    // 업체 삭제
    @DeleteMapping("/{company_id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("company_id") UUID id){
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public void test(@AuthenticationPrincipal UserDetails user){
        log.info("users: {}", user);
    }
}
