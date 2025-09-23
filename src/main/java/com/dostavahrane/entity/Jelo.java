package com.dostavahrane.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "jela")
public class Jelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String naziv;
    
    @Column(columnDefinition = "TEXT")
    private String opis;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cena;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restoran_id", nullable = false)
    private Restoran restoran;
    
    @OneToMany(mappedBy = "jelo", cascade = CascadeType.ALL)
    private List<StavkaPorudzbine> stavkePorudzbine;
    
    // Constructors
    public Jelo() {}
    
    public Jelo(String naziv, String opis, BigDecimal cena, Restoran restoran) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.restoran = restoran;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public BigDecimal getCena() { return cena; }
    public void setCena(BigDecimal cena) { this.cena = cena; }
    
    public Restoran getRestoran() { return restoran; }
    public void setRestoran(Restoran restoran) { this.restoran = restoran; }
    
    public List<StavkaPorudzbine> getStavkePorudzbine() { return stavkePorudzbine; }
    public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) { this.stavkePorudzbine = stavkePorudzbine; }
}