package com.onetool.server.diabetes.dto;

import com.onetool.server.diabetes.Diabetes;
import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
public record DiabetesResponse(
        Long id,
        String diabetesName,
        String category,
        Long standardPrice,
        String diabetesDetails,
        String diabetesImg,
        String diabetesDetailsImg,
        String capacity,
        String calorie,
        String storage,
        BigInteger hits,
        Long salePrice,
        LocalDateTime saleExpiredDate
) {
    @Builder
    public static DiabetesResponse fromEntity(Diabetes diabetes) {
        return new DiabetesResponse(
                diabetes.getId(),
                diabetes.getDiabetesName(),
                diabetes.getCategory(),
                diabetes.getStandardPrice(),
                diabetes.getDiabetesDetails(),
                diabetes.getDiabetesImg(),
                diabetes.getDiabetesDetailsImg(),
                diabetes.getCapacity(),
                diabetes.getCalorie(),
                diabetes.getStorage(),
                diabetes.getHits(),
                diabetes.getSalePrice(),
                diabetes.getSaleExpiredDate()
        );
    }

    public static DiabetesResponse items(Diabetes diabetes) {
        return DiabetesResponse.builder()
                .id(diabetes.getId())
                .diabetesName(diabetes.getDiabetesName())
                .standardPrice(diabetes.getStandardPrice())
                .salePrice(diabetes.getSalePrice())
                .diabetesImg(diabetes.getDiabetesImg())
                .build();
    }
}