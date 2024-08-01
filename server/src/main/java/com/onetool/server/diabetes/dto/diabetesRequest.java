package com.onetool.server.diabetes.dto;

import com.onetool.server.blueprint.Blueprint;
import com.onetool.server.blueprint.dto.BlueprintRequest;
import lombok.Builder;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record diabetesRequest(
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
    public static diabetesRequest fromEntity(Blueprint blueprint) {
        return new diabetesRequest(
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
}