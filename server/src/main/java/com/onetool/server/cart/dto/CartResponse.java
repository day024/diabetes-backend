package com.onetool.server.cart.dto;

import com.onetool.server.blueprint.dto.BlueprintResponse;
import com.onetool.server.cart.CartBlueprint;
import com.onetool.server.diabetes.dto.DiabetesResponse;
import lombok.Builder;

import java.util.List;

public class CartResponse {
    @Builder
    public record CartItems(
            Long totalPrice,
            List<DiabetesResponse> diabetesResponses
    ){
        public static CartItems cartItems(Long totalPrice, List<CartBlueprint> cartBlueprints){
            return CartItems.builder()
                    .totalPrice(totalPrice)
                    .diabetesResponses(cartBlueprints.stream()
                            .map(cartItem -> DiabetesResponse.items(cartItem.getDiabetes()))
                            .toList())
                    .build();
        }
    }
}
