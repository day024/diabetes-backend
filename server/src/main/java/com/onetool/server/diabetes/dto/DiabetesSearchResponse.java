package com.onetool.server.diabetes.dto;

import com.onetool.server.diabetes.Diabetes;
import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
public record DiabetesSearchResponse(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
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

    public static DiabetesSearchResponse from(Diabetes diabetes) {
        return DiabetesSearchResponse.builder()
                .createdAt(diabetes.getCreatedAt())
                .updatedAt(diabetes.getUpdatedAt())
                .id(diabetes.getId())
                .diabetesName(diabetes.getDiabetesName())
                .category(diabetes.getCategory())
                .standardPrice(diabetes.getStandardPrice())
                .diabetesDetails(diabetes.getDiabetesDetails())
                .diabetesImg(diabetes.getDiabetesImg())
                .capacity(diabetes.getCapacity())
                .calorie(diabetes.getCalorie())
                .hits(diabetes.getHits())
                .salePrice(diabetes.getSalePrice())
                .saleExpiredDate(diabetes.getSaleExpiredDate())
                .build();
    }
}