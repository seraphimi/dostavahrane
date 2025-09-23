package com.dostavahrane.repository;

import com.dostavahrane.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
    List<Porudzbina> findByKorisnikId(Long korisnikId);
    List<Porudzbina> findByStatus(Porudzbina.StatusPorudzbine status);
}