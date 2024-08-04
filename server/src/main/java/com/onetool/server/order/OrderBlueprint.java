package com.onetool.server.order;

import com.onetool.server.blueprint.Blueprint;
import com.onetool.server.diabetes.Diabetes;
import com.onetool.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity (name = "order_blueprint")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBlueprint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_blueprint_id")
    private Blueprint blueprint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_diabetes_id")
    private Diabetes diabetes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Orders order;

    @Builder
    private OrderBlueprint(Orders orders, Diabetes diabetes){
        this.diabetes = diabetes;
        this.order = orders;
    }

    public static OrderBlueprint newOrderBluePrint(Orders orders, Diabetes diabetes){
        return OrderBlueprint.builder()
                .orders(orders)
                .diabetes(diabetes)
                .build();
    }
}