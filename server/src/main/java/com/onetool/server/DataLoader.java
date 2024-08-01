package com.onetool.server;

import com.onetool.server.category.FirstCategory;
import com.onetool.server.category.FirstCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {

    private final FirstCategoryRepository firstCategoryRepository;

    public DataLoader(FirstCategoryRepository firstCategoryRepository) {
        this.firstCategoryRepository = firstCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createCategoryIfNotExists(1L, "building");
        createCategoryIfNotExists(2L, "civil");
        createCategoryIfNotExists(3L, "interior");
        createCategoryIfNotExists(4L, "machine");
        createCategoryIfNotExists(5L, "electric");
        createCategoryIfNotExists(6L, "etc");
        createCategoryIfNotExists(7L, "category1");
        createCategoryIfNotExists(8L, "category2");
        createCategoryIfNotExists(9L, "category3");
        createCategoryIfNotExists(10L, "category4");
        createCategoryIfNotExists(11L, "category5");
    }

    private void createCategoryIfNotExists(Long id, String name) {
        Optional<FirstCategory> existingCategory = firstCategoryRepository.findById(id);
        if (!existingCategory.isPresent()) {
            FirstCategory category = FirstCategory.builder()
                    .id(id)
                    .name(name)
                    .build();
            firstCategoryRepository.save(category);
        }
    }
}