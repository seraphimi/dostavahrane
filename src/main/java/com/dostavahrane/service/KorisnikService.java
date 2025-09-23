package com.dostavahrane.service;

import com.dostavahrane.entity.Korisnik;
import com.dostavahrane.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {
    
    @Autowired
    private KorisnikRepository korisnikRepository;
    
    public Korisnik registrujKorisnika(Korisnik korisnik) {
        if (korisnikRepository.existsByUsername(korisnik.getUsername())) {
            throw new RuntimeException("Username već postoji");
        }
        return korisnikRepository.save(korisnik);
    }
    
    public Korisnik login(String username, String password) {
        Optional<Korisnik> korisnik = korisnikRepository.findByUsername(username);
        if (korisnik.isPresent() && korisnik.get().getPassword().equals(password)) {
            return korisnik.get();
        }
        throw new RuntimeException("Pogrešno korisničko ime ili lozinka");
    }
    
    public Korisnik findById(Long id) {
        return korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
    }
}