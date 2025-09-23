package com.dostavahrane.service;

import com.dostavahrane.entity.Jelo;
import com.dostavahrane.repository.JeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeloService {
    
    @Autowired
    private JeloRepository jeloRepository;
    
    public List<Jelo> findAll() {
        return jeloRepository.findAll();
    }
    
    public List<Jelo> findByRestoranId(Long restoranId) {
        return jeloRepository.findByRestoranId(restoranId);
    }
    
    public Jelo save(Jelo jelo) {
        return jeloRepository.save(jelo);
    }
    
    public Jelo findById(Long id) {
        return jeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jelo nije pronađeno"));
    }
    
    public Jelo update(Long id, Jelo jelo) {
        Jelo existing = findById(id);
        existing.setNaziv(jelo.getNaziv());
        existing.setOpis(jelo.getOpis());
        existing.setCena(jelo.getCena());
        if (jelo.getRestoran() != null) {
            existing.setRestoran(jelo.getRestoran());
        }
        return jeloRepository.save(existing);
    }
    
    public void delete(Long id) {
        jeloRepository.deleteById(id);
    }
}