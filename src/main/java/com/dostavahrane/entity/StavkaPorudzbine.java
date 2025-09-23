package com.dostavahrane.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stavke_porudzbine")
public class StavkaPorudzbine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "porudzbina_id", nullable = false)
    private Porudzbina porudzbina;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jelo_id", nullable = false)  
    private Jelo jelo;
    
    @Column(nullable = false)
    private Integer kolicina;
    
    // Constructors
    public StavkaPorudzbine() {}
    
    public StavkaPorudzbine(Porudzbina porudzbina, Jelo jelo, Integer kolicina) {
        this.porudzbina = porudzbina;
        this.jelo = jelo;
        this.kolicina = kolicina;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Porudzbina getPorudzbina() { return porudzbina; }
    public void setPorudzbina(Porudzbina porudzbina) { this.porudzbina = porudzbina; }
    
    public Jelo getJelo() { return jelo; }
    public void setJelo(Jelo jelo) { this.jelo = jelo; }
    
    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }
}