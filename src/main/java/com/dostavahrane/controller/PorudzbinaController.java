package com.dostavahrane.controller;

import com.dostavahrane.entity.Porudzbina;
import com.dostavahrane.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/porudzbine")
public class PorudzbinaController {
    
    @Autowired
    private PorudzbinaService porudzbinaService;
    
    @PostMapping
    public ResponseEntity<Porudzbina> createPorudzbina(@RequestBody Porudzbina porudzbina) {
        Porudzbina createdPorudzbina = porudzbinaService.save(porudzbina);
        return ResponseEntity.ok(createdPorudzbina);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Porudzbina> getPorudzbina(@PathVariable Long id) {
        try {
            Porudzbina porudzbina = porudzbinaService.findById(id);
            return ResponseEntity.ok(porudzbina);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Porudzbina> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusData) {
        try {
            String statusString = statusData.get("status");
            Porudzbina.StatusPorudzbine status = Porudzbina.StatusPorudzbine.valueOf(statusString);
            Porudzbina updatedPorudzbina = porudzbinaService.updateStatus(id, status);
            return ResponseEntity.ok(updatedPorudzbina);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}