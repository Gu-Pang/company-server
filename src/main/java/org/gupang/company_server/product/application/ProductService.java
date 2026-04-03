package org.gupang.company_server.product.application;

import lombok.RequiredArgsConstructor;
import org.gupang.company_server.product.application.dto.ProductServiceDto;
import org.gupang.company_server.product.domain.Product;
import org.gupang.company_server.product.domain.ProductRepository;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.gupang.company_server.product.domain.service.RoleCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RoleCheck roleCheck;
    private final CompanyProvider provider;

    @Transactional
    public UUID create(ProductServiceDto.Create dto) {

        Product product = Product.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .price(dto.getPrice())
                .rolecheck(roleCheck)
                .provider(provider)
                .build();

        productRepository.save(product);

        return product.getId();
    }
}