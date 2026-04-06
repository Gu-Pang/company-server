package org.gupang.company_server.product.application;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.product.application.dto.ProductServiceDto;
import org.gupang.company_server.product.domain.Product;
import org.gupang.company_server.product.domain.ProductRepository;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.gupang.company_server.product.domain.service.RoleCheck;
import org.gupang.company_server.product.presentation.dto.ProductResponseDto;
import org.gupang.company_server.shared.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RoleCheck roleCheck;
    private final CompanyProvider provider;

    // 상품생성
    @Transactional
    public UUID create(ProductServiceDto.Create dto) {

        Product product = Product.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .price(dto.getPrice())
                .rolecheck(roleCheck)
                .provider(provider)
                .companyId(dto.getCompanyId())
                .build();

        productRepository.save(product);
        return product.getId();
    }

    // 상품 상세 조회
    public ProductResponseDto getProduct(UUID id) {
        return productRepository.findById(id)
                .map(ProductResponseDto::from)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    // 상품 전체 조회
    public Page<ProductResponseDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductResponseDto::from);
    }

    // 상품 수정
    @Transactional
    public void update(UUID id, ProductServiceDto.Update dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.updateInfo(dto.getName(), dto.getStock(), dto.getPrice(), roleCheck);
    }

    // 상품 삭제
    @Transactional
    public void delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.delete(roleCheck);
    }

    // 재고 추가
    @Transactional
    public void addStock(UUID id, ProductServiceDto.UpdateStock dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.addStock(dto.getAmount());
    }

    // 재고 차감
    @Transactional
    public void reduceStock(UUID id, ProductServiceDto.UpdateStock dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.reduceStock(dto.getAmount());
    }
}