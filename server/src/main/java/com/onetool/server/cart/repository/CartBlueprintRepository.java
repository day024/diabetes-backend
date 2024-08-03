package com.onetool.server.cart.repository;

import com.onetool.server.cart.Cart;
import com.onetool.server.cart.CartBlueprint;
import com.onetool.server.diabetes.Diabetes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartBlueprintRepository extends JpaRepository<CartBlueprint, Long> {
    boolean existsByCartAndDiabetes(Cart cart, Diabetes diabetes);
}
