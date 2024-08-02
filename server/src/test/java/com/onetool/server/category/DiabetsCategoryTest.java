package com.onetool.server.category;

import com.onetool.server.diabetes.dto.DiabetesSearchResponse;
import com.onetool.server.diabetes.service.DiabetesService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DiabetsCategoryTest {
    @Autowired
    private DiabetesService diabetesService;

    @DisplayName("category1 카테고리를 가진 간편식이 잘 나오는지 확인")
    @Test
    void search_first_category_category1() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<DiabetesSearchResponse> response = diabetesService.findAllByFirstCategory("category1", pageable);
        assertThat(response.getTotalElements()).isEqualTo(2);
    }

    @DisplayName("category2 카테고리를 가진 간편식이 잘 나오는지 확인")
    @Test
    void search_first_category_category2() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<DiabetesSearchResponse> response = diabetesService.findAllByFirstCategory("category2", pageable);
        assertThat(response.getTotalElements()).isEqualTo(1);
    }

    @DisplayName("category3 카테고리를 가진 간편식이 잘 나오는지 확인")
    @Test
    void search_first_category_category3() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<DiabetesSearchResponse> response = diabetesService.findAllByFirstCategory("category3", pageable);
        assertThat(response.getTotalElements()).isEqualTo(1);
    }

    @DisplayName("category4 카테고리를 가진 간편식이 잘 나오는지 확인")
    @Test
    void search_first_category_category4() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<DiabetesSearchResponse> response = diabetesService.findAllByFirstCategory("category4", pageable);
        assertThat(response.getTotalElements()).isEqualTo(1);
    }

    @DisplayName("category5 카테고리를 가진 간편식이 잘 나오는지 확인")
    @Test
    void search_first_category_category5() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<DiabetesSearchResponse> response = diabetesService.findAllByFirstCategory("category5", pageable);
        assertThat(response.getTotalElements()).isEqualTo(1);
    }
}