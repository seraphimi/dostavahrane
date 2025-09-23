package com.dostavahrane.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "korisnici")
public class Korisnik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String ime;
    
    @Column(nullable = false)
    private String prezime;
    
    @Column(nullable = false)
    private String adresa;
    
    @Column(nullable = false)
    private String kontakt;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Porudzbina> porudzbine;
    
    // Constructors
    public Korisnik() {}
    
    public Korisnik(String ime, String prezime, String adresa, String kontakt, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.username = username;
        this.password = password;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    
    public String getKontakt() { return kontakt; }
    public void setKontakt(String kontakt) { this.kontakt = kontakt; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public List<Porudzbina> getPorudzbine() { return porudzbine; }
    public void setPorudzbine(List<Porudzbina> porudzbine) { this.porudzbine = porudzbine; }
}