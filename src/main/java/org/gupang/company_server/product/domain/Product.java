package org.gupang.company_server.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gupang.common.entity.BaseEntity;
import org.gupang.common.entity.UserRole;
import org.gupang.common.exception.CustomException;
import org.gupang.company_server.company.domain.Company;
import org.gupang.company_server.product.domain.service.CompanyProvider;
import org.gupang.company_server.product.domain.service.RoleCheck;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "p_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

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

        if (stock <= 0) {
            throw new RuntimeException("재고는 0보다 작을 수 없습니다.");
        }

        if (price <= 0) {
            throw new RuntimeException("상품 가격은 0보다 작을 수 없습니다.");
        }

        this.name = name;
        this.stock = stock;
        this.price = price;

        this.company = new CompanyInfo(companyId, provider);
    }

    public void reduceStock(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("차감할 재고는 1개 이상 입력하세요.");
        }

        if (this.stock - amount < 0) {
            throw new RuntimeException("재고 차감은 현재 남아 있는 재고(%d) 이하여야 됩니다.");
        }

        this.stock -= amount;
    }

    public void addStock(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("차감할 재고는 1개 이상 입력하세요.");
        }

        this.stock += amount;
    }

    public void delete(RoleCheck rolecheck) {
        // 권한 체크
        checkAuthority(rolecheck);
        this.isDeleted = true;
    }

    private void checkAuthority(RoleCheck roleCheck) {
        if (roleCheck.hasRole(UserRole.MANAGER)) return;

        if (roleCheck.hasRole(UserRole.COMPANY)) {
            // 상품 수정 또는 삭제인 경우는 등록한 업체의 상품인지 체크
            if (id != null && !roleCheck.isMyCompany(company.getId())) {
                throw new RuntimeException("상품을 등록한 업체만 처리 가능합니다.");
            }
        }
    }

}
