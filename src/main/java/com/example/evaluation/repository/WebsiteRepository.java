package com.example.evaluation.repository;

import com.example.evaluation.model.EvaluationItem;
import com.example.evaluation.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website,Integer> {
}
