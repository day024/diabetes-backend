package com.onetool.server.diabetes.dto;

import com.onetool.server.diabetes.Diabetes;
import lombok.Builder;

public record DiabetesSearchResponse(
        Diabetes diabetes
) {

    @Builder
    public DiabetesSearchResponse(Diabetes diabetes) {
        this.diabetes = diabetes;
    }

    public static DiabetesSearchResponse from(Diabetes diabetes) {
        return DiabetesSearchResponse.builder()
                .diabetes(diabetes)
                .build();
    }
}