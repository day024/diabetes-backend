package com.onetool.server.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirstCategoryRepository extends JpaRepository<FirstCategory, Long> {
}