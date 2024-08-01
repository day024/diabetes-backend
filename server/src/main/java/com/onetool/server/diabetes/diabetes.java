package com.onetool.server.diabetes;

import com.onetool.server.cart.CartBlueprint;
import com.onetool.server.global.entity.BaseEntity;
import com.onetool.server.order.OrderBlueprint;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class diabetes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="diabetes_name")
    private String diabetesName;
    @Column(name="category_id")
    private Long categoryId;
    @Column(name = "second_category")
    private String secondCategory;
    @Column(name = "standard_price")
    private Long standardPrice;
    @Column(name = "diabetes_img")
    private String diabetesImg;
    @Column(name = "blueprint_details")
    private String diabetesDetails;
    @Column(name = "extension")
    private String extension;
    @Column(name = "hits")
    private BigInteger hits;
    @Column(name = "sale_price")
    private Long salePrice;
    @Column(name = "sale_expired_date")
    private LocalDateTime saleExpiredDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_blueprint_id")
    private OrderBlueprint orderBlueprint;

    @OneToMany(mappedBy = "blueprint")
    private List<CartBlueprint> cartBlueprints = new ArrayList<>();

}