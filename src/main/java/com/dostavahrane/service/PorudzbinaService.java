package com.dostavahrane.service;

import com.dostavahrane.entity.Porudzbina;
import com.dostavahrane.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PorudzbinaService {
    
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    
    public Porudzbina save(Porudzbina porudzbina) {
        Porudzbina savedPorudzbina = porudzbinaRepository.save(porudzbina);
        // Pokretanje dostave u novoj niti
        startDelivery(savedPorudzbina.getId());
        return savedPorudzbina;
    }
    
    public Porudzbina findById(Long id) {
        return porudzbinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porudžbina nije pronađena"));
    }
    
    public List<Porudzbina> findByKorisnikId(Long korisnikId) {
        return porudzbinaRepository.findByKorisnikId(korisnikId);
    }
    
    public Porudzbina updateStatus(Long id, Porudzbina.StatusPorudzbine status) {
        Porudzbina porudzbina = findById(id);
        porudzbina.setStatus(status);
        return porudzbinaRepository.save(porudzbina);
    }
    
    @Async
    public CompletableFuture<Void> startDelivery(Long porudzbinaId) {
        try {
            System.out.println("Glavna nit: Pokretanje dostave za porudžbinu ID " + porudzbinaId);
            
            // Simulacija pripreme (2 sekunde)
            Thread.sleep(2000);
            updateStatus(porudzbinaId, Porudzbina.StatusPorudzbine.U_PRIPREMI);
            System.out.println("Porudžbina " + porudzbinaId + " je u pripremi");
            
            // Simulacija pripreme hrane (3 sekunde)
            Thread.sleep(3000);
            updateStatus(porudzbinaId, Porudzbina.StatusPorudzbine.SPREMNA);
            System.out.println("Porudžbina " + porudzbinaId + " je spremna");
            
            // Simulacija slanja na dostavu (2 sekunde)
            Thread.sleep(2000);
            updateStatus(porudzbinaId, Porudzbina.StatusPorudzbine.U_DOSTAVI);
            System.out.println("Porudžbina " + porudzbinaId + " je poslata na dostavu");
            
            // Druga nit čeka 10 sekundi i označava kao isporučeno
            deliverOrder(porudzbinaId);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Dostava prekinuta za porudžbinu " + porudzbinaId);
        }
        
        return CompletableFuture.completedFuture(null);
    }
    
    @Async
    public CompletableFuture<Void> deliverOrder(Long porudzbinaId) {
        try {
            // Druga nit čeka 10 sekundi
            Thread.sleep(10000);
            updateStatus(porudzbinaId, Porudzbina.StatusPorudzbine.ISPORUCENA);
            System.out.println("DRUGA NIT: Porudžbina " + porudzbinaId + " je uspešno isporučena!");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Isporuka prekinuta za porudžbinu " + porudzbinaId);
        }
        
        return CompletableFuture.completedFuture(null);
    }
}