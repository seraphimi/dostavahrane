package com.dostavahrane.repository;

import com.dostavahrane.entity.Jelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JeloRepository extends JpaRepository<Jelo, Long> {
    List<Jelo> findByRestoranId(Long restoranId);
}