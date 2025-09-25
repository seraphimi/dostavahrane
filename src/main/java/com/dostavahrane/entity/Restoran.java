package com.dostavahrane.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name = "restorani")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restoran {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String naziv;
    
    @Column(nullable = false)
    private String adresa;
    
    @Column(nullable = false)
    private String tipKuhinje;
    
    @OneToMany(mappedBy = "restoran", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Jelo> jela;
    
    @OneToMany(mappedBy = "restoran", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Porudzbina> porudzbine;
    
    // Constructors
    public Restoran() {}
    
    public Restoran(String naziv, String adresa, String tipKuhinje) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.tipKuhinje = tipKuhinje;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    
    public String getTipKuhinje() { return tipKuhinje; }
    public void setTipKuhinje(String tipKuhinje) { this.tipKuhinje = tipKuhinje; }
    
    public List<Jelo> getJela() { return jela; }
    public void setJela(List<Jelo> jela) { this.jela = jela; }
    
    public List<Porudzbina> getPorudzbine() { return porudzbine; }
    public void setPorudzbine(List<Porudzbina> porudzbine) { this.porudzbine = porudzbine; }
}