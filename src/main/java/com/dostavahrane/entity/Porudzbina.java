package com.dostavahrane.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "porudzbine")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Porudzbina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restoran_id", nullable = false)
    private Restoran restoran;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ukupanIznos;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPorudzbine status;
    
    @Column(nullable = false)
    private LocalDateTime vremeKreiranja;
    
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StavkaPorudzbine> stavke;
    
    // Enum for order status
    public enum StatusPorudzbine {
        KREIRANA, U_PRIPREMI, SPREMNA, U_DOSTAVI, ISPORUCENA, OTKAZANA
    }
    
    // Constructors
    public Porudzbina() {
        this.vremeKreiranja = LocalDateTime.now();
        this.status = StatusPorudzbine.KREIRANA;
    }
    
    public Porudzbina(Korisnik korisnik, Restoran restoran, BigDecimal ukupanIznos) {
        this();
        this.korisnik = korisnik;
        this.restoran = restoran;
        this.ukupanIznos = ukupanIznos;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Korisnik getKorisnik() { return korisnik; }
    public void setKorisnik(Korisnik korisnik) { this.korisnik = korisnik; }
    
    public Restoran getRestoran() { return restoran; }
    public void setRestoran(Restoran restoran) { this.restoran = restoran; }
    
    public BigDecimal getUkupanIznos() { return ukupanIznos; }
    public void setUkupanIznos(BigDecimal ukupanIznos) { this.ukupanIznos = ukupanIznos; }
    
    public StatusPorudzbine getStatus() { return status; }
    public void setStatus(StatusPorudzbine status) { this.status = status; }
    
    public LocalDateTime getVremeKreiranja() { return vremeKreiranja; }
    public void setVremeKreiranja(LocalDateTime vremeKreiranja) { this.vremeKreiranja = vremeKreiranja; }
    
    public List<StavkaPorudzbine> getStavke() { return stavke; }
    public void setStavke(List<StavkaPorudzbine> stavke) { this.stavke = stavke; }
}