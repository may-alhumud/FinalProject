package com.example.evaluation.repository;

import com.example.evaluation.model.EvaluationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationItemRepository extends JpaRepository<EvaluationItem,Integer> {
}
