package com.onetool.server.diabetes.dto;

import com.onetool.server.diabetes.Diabetes;
import lombok.Builder;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record DiabetesRequest(
        Long id,
        String diabetesName,
        Long categoryId,
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
    public static DiabetesRequest fromEntity(Diabetes diabetes) {
        return new DiabetesRequest(
                diabetes.getId(),
                diabetes.getDiabetesName(),
                diabetes.getCategoryId(),
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
}