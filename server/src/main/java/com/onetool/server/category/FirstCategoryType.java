package com.onetool.server.category;

import lombok.Getter;

@Getter
public enum FirstCategoryType {
    CATEGORY_BUILDING("building"),
    CATEGORY_CIVIL("civil"),
    CATEGORY_INTERIOR("interior"),
    CATEGORY_MACHINE("machine"),
    CATEGORY_ELECTRIC("electric"),
    CATEGORY_ETC("etc"),
    CATEGORY_CATEGORY1("category1"),
    CATEGORY_CATEGORY2("category2"),
    CATEGORY_CATEGORY3("category3"),
    CATEGORY_CATEGORY4("category4"),
    CATEGORY_CATEGORY5("category5");

    private final String type;

    FirstCategoryType(String type) {
        this.type = type;
    }

}