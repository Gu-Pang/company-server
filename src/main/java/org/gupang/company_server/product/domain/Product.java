package org.gupang.company_server.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gupang.common.entity.BaseEntity;
import org.gupang.common.entity.UserRole;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.shared.exception.ErrorCode;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.gupang.company_server.product.domain.service.RoleCheck;

import java.util.UUID;

@Entity
@Table(name = "p_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    private static final int MINIMUM_STOCK_COUNT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;

    @Embedded
    private CompanyInfo company;

    @Column(nullable = false)
    private String name;

    private int stock;

    private long price;

    private boolean isDeleted;

    @Builder
    public Product(String name, int  stock, long price, UUID companyId, CompanyProvider provider, RoleCheck rolecheck) {
        // 권한 체크
        checkAuthority(rolecheck);
        validateStock(stock);
        validatePrice(price);

        this.name = name;
        this.stock = stock;
        this.price = price;
        this.company = new CompanyInfo(companyId, provider);
    }

    public void reduceStock(int amount) {
        validateStock(amount);

        if (this.stock - amount < 0) {
            throw new CustomException(ErrorCode.INSUFFICIENT_STOCK);
        }

        this.stock -= amount;
    }

    public void addStock(int amount) {
        validateStock(amount);
        this.stock += amount;
    }

    public void delete(RoleCheck rolecheck) {
        checkAuthority(rolecheck);
        this.isDeleted = true;
    }

    private void validateStock(int amount) {
        if (amount <= MINIMUM_STOCK_COUNT) {
            throw new CustomException(ErrorCode.INVALID_STOCK_QUANTITY);
        }
    }

    private void validatePrice(long price) {
        if (price <= 0) {
            throw new CustomException(ErrorCode.INVALID_PRODUCT_PRICE);
        }
    }

    private void checkAuthority(RoleCheck roleCheck) {
        if (roleCheck.hasRole(UserRole.MANAGER)) return;

        if (roleCheck.hasRole(UserRole.COMPANY)) {
            if (id != null && !roleCheck.isMyCompany(company.getId())) {
                throw new CustomException(ErrorCode.UNAUTHORIZED_COMPANY);
            }
        }
    }
}