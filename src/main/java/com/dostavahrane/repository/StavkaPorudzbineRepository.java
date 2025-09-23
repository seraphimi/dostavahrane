package com.dostavahrane.repository;

import com.dostavahrane.entity.StavkaPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Long> {
}