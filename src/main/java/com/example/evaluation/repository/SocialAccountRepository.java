package com.example.evaluation.repository;

import com.example.evaluation.model.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAccountRepository extends JpaRepository<SocialAccount,Integer> {
    
}
