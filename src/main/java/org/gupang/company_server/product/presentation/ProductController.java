package org.gupang.company_server.product.presentation;

import lombok.RequiredArgsConstructor;
import org.gupang.company_server.product.application.ProductService;
import org.gupang.company_server.product.application.dto.ProductServiceDto;
import org.gupang.company_server.product.presentation.dto.ProductRequestDto;
import org.gupang.company_server.product.presentation.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody ProductRequestDto request) {
        ProductServiceDto.Create command = ProductServiceDto.Create.builder()
                .name(request.name())
                .stock(request.stock())
                .price(request.price())
                .companyId(request.companyId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(command));
    }

    // 상품 상세 조회
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("product_id") UUID id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/list/{product_ids}")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@PathVariable("product_ids") List<UUID> ids) {
        return ResponseEntity.ok(productService.getProducts(ids));
    }

    // 상품 전체 조회 (Paging)
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    // 상품 수정
    @PatchMapping("/{product_id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("product_id") UUID id,
            @RequestBody ProductRequestDto request) {

        ProductServiceDto.Update command = ProductServiceDto.Update.builder()
                .name(request.name())
                .stock(request.stock())
                .price(request.price())
                .build();

        productService.update(id, command);
        return ResponseEntity.noContent().build();
    }

    // 상품 삭제
    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("product_id") UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 상품 재고 추가
    @PatchMapping("/{product_id}/add-stock")
    public ResponseEntity<Void> addStock(
            @PathVariable("product_id") UUID id,
            @RequestBody ProductServiceDto.UpdateStock dto) {
        productService.addStock(id, dto);
        return ResponseEntity.noContent().build();
    }

    // 상품 재고 감소
    @PatchMapping("/{product_id}/reduce-stock")
    public ResponseEntity<Void> reduceStock(
            @PathVariable("product_id") UUID id,
            @RequestBody ProductServiceDto.UpdateStock dto) {
        productService.reduceStock(id, dto);
        return ResponseEntity.noContent().build();
    }
}