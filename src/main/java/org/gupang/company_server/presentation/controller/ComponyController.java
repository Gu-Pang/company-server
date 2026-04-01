package org.gupang.company_server.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.gupang.company_server.domain.company.model.Company;
import org.gupang.company_server.domain.company.service.CompanyService;
import org.gupang.company_server.presentation.dto.CompanyRequestDto;
import org.gupang.company_server.presentation.dto.CompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class ComponyController {
    private final CompanyService  companyService;

    // 업체 생성
    @PostMapping
    public ResponseEntity<UUID> creteCompany(@RequestBody CompanyRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(requestDto));
    }

    // 업체 수정
    @PatchMapping("/{company_id}")
    public ResponseEntity<Void> upateCompany(@PathVariable("company_id") UUID id, @RequestBody CompanyRequestDto requestDto){
        companyService.updateCompany(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    // 업체 전체 조회
    @GetMapping
    public ResponseEntity<Page<CompanyResponseDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(companyService.getCompanies(pageable));
    }

    // 업체 상세 조회
    @GetMapping("/{company_id")
    public ResponseEntity<CompanyResponseDto> getOne(@PathVariable("company_id") UUID id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    // 업체 삭제
    @DeleteMapping("/{company_id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("company_id") UUID id){
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
