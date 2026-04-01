package org.gupang.company_server.domain.company.model;

import jakarta.persistence.*;
import lombok.*;
import org.gupang.entity.BaseEntity;

import java.util.UUID;

@Entity
@Table(name = "p_companies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "company_id")
    private UUID id;

    @Column(name = "company_name", nullable = false)
    private String name;

    @Column(name = "company_address", nullable = false)
    private String address;

    @Column(name = "company_address_detail")
    private String addressDetail;

    @Column(name = "hub_id", nullable = false)
    private UUID hubId;

    @Column(name = "company_manager_id", nullable = false)
    private UUID managerId;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void update(String name, String address, String addressDetail){
        this.name = name;
        this.address = address;
        this.addressDetail = addressDetail;
    }
    
    public void delete(){
        this.isDeleted = true;
    }

}
