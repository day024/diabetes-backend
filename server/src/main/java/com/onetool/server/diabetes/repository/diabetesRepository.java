package com.onetool.server.diabetes.repository;

import com.onetool.server.diabetes.Diabetes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiabetesRepository extends JpaRepository<Diabetes, Long> {
}