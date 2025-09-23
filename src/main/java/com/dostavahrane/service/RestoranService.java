package com.dostavahrane.service;

import com.dostavahrane.entity.Restoran;
import com.dostavahrane.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestoranService {
    
    @Autowired
    private RestoranRepository restoranRepository;
    
    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }
    
    public Restoran save(Restoran restoran) {
        return restoranRepository.save(restoran);
    }
    
    public Restoran findById(Long id) {
        return restoranRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restoran nije pronađen"));
    }
    
    public Restoran update(Long id, Restoran restoran) {
        Restoran existing = findById(id);
        existing.setNaziv(restoran.getNaziv());
        existing.setAdresa(restoran.getAdresa());
        existing.setTipKuhinje(restoran.getTipKuhinje());
        return restoranRepository.save(existing);
    }
    
    public void delete(Long id) {
        restoranRepository.deleteById(id);
    }
}