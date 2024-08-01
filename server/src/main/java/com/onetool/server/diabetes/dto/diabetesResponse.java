package com.onetool.server.diabetes.dto;

import com.onetool.server.blueprint.Blueprint;
import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
public record diabetesResponse(
        Long id,
        String blueprintName,
        Long categoryId,
        Long standardPrice,
        String blueprintImg,
        String blueprintDetails,
        String extension,
        String program,
        BigInteger hits,
        Long salePrice,
        LocalDateTime saleExpiredDate,
        String creatorName,
        String downloadLink
)  {
    @Builder
    public static diabetesResponse fromEntity(Blueprint blueprint) {
        return new diabetesResponse(
                blueprint.getId(),
                blueprint.getBlueprintName(),
                blueprint.getCategoryId(),
                blueprint.getStandardPrice(),
                blueprint.getBlueprintImg(),
                blueprint.getBlueprintDetails(),
                blueprint.getExtension(),
                blueprint.getProgram(),
                blueprint.getHits(),
                blueprint.getSalePrice(),
                blueprint.getSaleExpiredDate(),
                blueprint.getCreatorName(),
                blueprint.getDownloadLink()
        );
    }
    public static diabetesResponse items(Blueprint blueprint){
        return diabetesResponse.builder()
                .id(blueprint.getId())
                .creatorName(blueprint.getCreatorName())
                .blueprintName(blueprint.getBlueprintName())
                .standardPrice(blueprint.getStandardPrice())
                .salePrice(blueprint.getSalePrice())
                .blueprintImg(blueprint.getBlueprintImg())
                .build();
    }
}