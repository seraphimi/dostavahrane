package com.dostavahrane.controller;

import com.dostavahrane.entity.Jelo;
import com.dostavahrane.entity.Restoran;
import com.dostavahrane.service.JeloService;
import com.dostavahrane.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restorani")
public class RestoranController {
    
    @Autowired
    private RestoranService restoranService;
    
    @Autowired
    private JeloService jeloService;
    
    @GetMapping
    public ResponseEntity<List<Restoran>> getAllRestorani() {
        List<Restoran> restorani = restoranService.findAll();
        return ResponseEntity.ok(restorani);
    }
    
    @PostMapping
    public ResponseEntity<Restoran> createRestoran(@RequestBody Restoran restoran) {
        Restoran createdRestoran = restoranService.save(restoran);
        return ResponseEntity.ok(createdRestoran);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Restoran> updateRestoran(@PathVariable Long id, @RequestBody Restoran restoran) {
        try {
            Restoran updatedRestoran = restoranService.update(id, restoran);
            return ResponseEntity.ok(updatedRestoran);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestoran(@PathVariable Long id) {
        try {
            restoranService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/meni")
    public ResponseEntity<List<Jelo>> getMeniRestorana(@PathVariable Long id) {
        try {
            // Proveravamo da li restoran postoji
            restoranService.findById(id);
            List<Jelo> meni = jeloService.findByRestoranId(id);
            return ResponseEntity.ok(meni);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}