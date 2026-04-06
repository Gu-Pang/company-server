package org.gupang.company_server.product.infrastructure.persistence;


import org.gupang.company_server.product.domain.Product;
import org.gupang.company_server.product.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID>, ProductRepository {
}