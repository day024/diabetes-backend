package com.onetool.server.cart;

import com.onetool.server.blueprint.Blueprint;
import com.onetool.server.diabetes.Diabetes;
import com.onetool.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartBlueprint extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //도면 상품은 도면 엔티티가 나오는 대로 짤게요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diabetes_id")
    private Diabetes diabetes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blueprint_id")
    private Blueprint blueprint;

    @Builder
    private CartBlueprint(Cart cart, Diabetes diabetes){
        this.cart = cart;
        this.diabetes = diabetes;
    }

    public static CartBlueprint newCartBlueprint(Cart cart, Diabetes diabetes){
        return CartBlueprint.builder()
                .cart(cart)
                .diabetes(diabetes)
                .build();
    }

}
