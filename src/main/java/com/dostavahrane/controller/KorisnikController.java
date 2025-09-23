package com.dostavahrane.controller;

import com.dostavahrane.entity.Korisnik;
import com.dostavahrane.entity.Porudzbina;
import com.dostavahrane.service.KorisnikService;
import com.dostavahrane.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {
    
    @Autowired
    private KorisnikService korisnikService;
    
    @Autowired
    private PorudzbinaService porudzbinaService;
    
    @PostMapping("/registracija")
    public ResponseEntity<Korisnik> registracija(@RequestBody Korisnik korisnik) {
        try {
            Korisnik registrovaniKorisnik = korisnikService.registrujKorisnika(korisnik);
            return ResponseEntity.ok(registrovaniKorisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");
            
            Korisnik korisnik = korisnikService.login(username, password);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("korisnik", korisnik);
            response.put("message", "Uspešno prijavljivanje");
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> getKorisnik(@PathVariable Long id) {
        try {
            Korisnik korisnik = korisnikService.findById(id);
            return ResponseEntity.ok(korisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{korisnikId}/porudzbine")
    public ResponseEntity<List<Porudzbina>> getPorudzbineKorisnika(@PathVariable Long korisnikId) {
        try {
            List<Porudzbina> porudzbine = porudzbinaService.findByKorisnikId(korisnikId);
            return ResponseEntity.ok(porudzbine);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}