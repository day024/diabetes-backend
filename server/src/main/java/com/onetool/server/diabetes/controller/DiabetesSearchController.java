package com.onetool.server.diabetes.controller;

import com.onetool.server.blueprint.service.BlueprintService;
import com.onetool.server.blueprint.dto.SearchResponse;
import com.onetool.server.category.FirstCategoryType;
import com.onetool.server.diabetes.dto.DiabetesSearchResponse;
import com.onetool.server.diabetes.service.DiabetesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiabetesSearchController {

    private final DiabetesService diabetesService;

    public DiabetesSearchController(DiabetesService diabetesService) {
        this.diabetesService = diabetesService;
    }

    @GetMapping("/diabetes")
    public ResponseEntity searchWithKeyword(
            @RequestParam("s")String keyword,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable
    ) {
        Page<DiabetesSearchResponse> response = diabetesService.searchByNameWithKeyword(keyword, pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/diabetes/c")
    public ResponseEntity searchCategory1(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
            @RequestParam("category") String category
    ) {
        Page<DiabetesSearchResponse> responses = diabetesService.findAllByFirstCategory(category, pageable);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/diabetes/all")
    public ResponseEntity searchAllBlueprint(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable
    ) {
        Page<DiabetesSearchResponse> responses = diabetesService.findAll(pageable);
        return ResponseEntity.ok().body(responses);
    }

}