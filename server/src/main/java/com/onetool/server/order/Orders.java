package com.onetool.server.order;

import com.onetool.server.diabetes.Diabetes;
import com.onetool.server.global.entity.BaseEntity;
import com.onetool.server.member.domain.Member;
import com.onetool.server.order.dto.request.OrderRequest;
import com.onetool.server.payments.Payments;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String orderCode = UUID.randomUUID().toString();

    @Setter
    private Long totalPrice;

    private int totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBlueprint> orderItems = new ArrayList<>();

    @OneToOne
    private Payments payments;

    @Builder
    private Orders(Long totalPrice){
        this.totalPrice = totalPrice;
    }

    public void mappingToMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
}
