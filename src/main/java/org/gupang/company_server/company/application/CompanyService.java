package org.gupang.company_server.company.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.company.domain.Company;
import org.gupang.company_server.company.domain.CompanyRepository;
import org.gupang.company_server.exception.ErrorCode;
import org.gupang.company_server.company.presentation.dto.CompanyRequestDto;
import org.gupang.company_server.company.presentation.dto.CompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    // 업체등록
    @Transactional
    public UUID createCompany(CompanyRequestDto requestDto){
        Company company = Company.builder()
                .name(requestDto.name())
                .address(requestDto.address())
                .addressDetail(requestDto.addressDetail())
                .hubId(requestDto.hubId())
                .managerId(requestDto.managerId())
                .build();
        return companyRepository.save(company).getId();
    }

    // 업체 수정
    @Transactional
    public void updateCompany(UUID id, CompanyRequestDto requestDto){
        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.COMPANY_NOT_FOUND));
        company.update(requestDto.name(), requestDto.address(),requestDto.addressDetail());
    }

    // 업체 상세 조회
    public CompanyResponseDto getCompany(UUID id){
        return companyRepository.findById(id)
                .map(CompanyResponseDto::from)
                .orElseThrow(()-> new CustomException(ErrorCode.COMPANY_NOT_FOUND));
    }

    // 업체 전체 조회
    public Page<CompanyResponseDto> getCompanies(Pageable pageable){
        return companyRepository.findAllByIsDeletedFalse(pageable)
                .map(CompanyResponseDto::from);
    }

    // 업체 삭제
    @Transactional
    public void deleteCompany(UUID id){
        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.COMPANY_NOT_FOUND));
        company.delete();
    }
}
