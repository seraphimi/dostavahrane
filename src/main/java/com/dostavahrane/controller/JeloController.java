package com.dostavahrane.controller;

import com.dostavahrane.entity.Jelo;
import com.dostavahrane.service.JeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jela")
public class JeloController {
    
    @Autowired
    private JeloService jeloService;
    
    @GetMapping
    public ResponseEntity<List<Jelo>> getAllJela() {
        List<Jelo> jela = jeloService.findAll();
        return ResponseEntity.ok(jela);
    }
    
    @PostMapping
    public ResponseEntity<Jelo> createJelo(@RequestBody Jelo jelo) {
        Jelo createdJelo = jeloService.save(jelo);
        return ResponseEntity.ok(createdJelo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Jelo> updateJelo(@PathVariable Long id, @RequestBody Jelo jelo) {
        try {
            Jelo updatedJelo = jeloService.update(id, jelo);
            return ResponseEntity.ok(updatedJelo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJelo(@PathVariable Long id) {
        try {
            jeloService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}