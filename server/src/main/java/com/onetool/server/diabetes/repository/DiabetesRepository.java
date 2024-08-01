package com.onetool.server.diabetes.repository;

import com.onetool.server.blueprint.Blueprint;
import com.onetool.server.diabetes.Diabetes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiabetesRepository extends JpaRepository<Diabetes, Long> {

    @Query("SELECT d FROM Diabetes d WHERE d.diabetesName LIKE %:keyword% ")
    Page<Diabetes> searchByNameWithKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Diabetes b WHERE b.categoryId IN (SELECT f.id FROM FirstCategory f WHERE f.name = :first)")
    Page<Diabetes> findAllByFirstCategory(@Param("first") String category, Pageable pageable);

    @Query("SELECT count(d) FROM Diabetes d")
    Long countAllDiabetes();
}