# Dostava Hrane - Spring Boot 3.0 RESTful Web Service

RESTful web servis za dostavu hrane implementiran sa Spring Boot 3.0 framework-om.

## Tehnički zahtevi

- **Spring Boot 3.0** sa JDK 17
- **MySQL** baza podataka (H2 za testiranje)
- **Višeslojna arhitektura** (Entity, Repository, Service, Controller)
- **AOP logging** sa custom klasom
- **Bez DTO** - direktno korišćenje entity klasa
- **Multithreading** za dostave sa @Async

## Entiteti

1. **Korisnik** - korisnici sistema (id, ime, prezime, adresa, kontakt, username, password)
2. **Restoran** - restorani (id, naziv, adresa, tipKuhinje)
3. **Jelo** - jela iz menija (id, naziv, opis, cena, restoran)
4. **Porudžbina** - porudžbine (id, korisnik, restoran, ukupanIznos, status, vremeKreiranja)
5. **StavkaPorudžbine** - stavke porudžbine (id, porudžbina, jelo, količina)

## API Endpoints

### Korisnici
- `POST /api/korisnici/registracija` - Registracija novog korisnika
- `POST /api/korisnici/login` - Prijava korisnika
- `GET /api/korisnici/{id}` - Dohvatanje korisnika po ID
- `GET /api/korisnici/{korisnikId}/porudzbine` - Porudžbine korisnika

### Restorani
- `GET /api/restorani` - Lista svih restorana
- `POST /api/restorani` - Kreiranje novog restorana  
- `PUT /api/restorani/{id}` - Ažuriranje restorana
- `DELETE /api/restorani/{id}` - Brisanje restorana
- `GET /api/restorani/{id}/meni` - Meni restorana

### Jela
- `GET /api/jela` - Lista svih jela
- `POST /api/jela` - Kreiranje novog jela
- `PUT /api/jela/{id}` - Ažuriranje jela  
- `DELETE /api/jela/{id}` - Brisanje jela

### Porudžbine
- `POST /api/porudzbine` - Kreiranje nove porudžbine (pokreće async dostavu)
- `GET /api/porudzbine/{id}` - Dohvatanje porudžbine po ID
- `PUT /api/porudzbine/{id}/status` - Ažuriranje statusa porudžbine

## Multithreading funkcionalnost

Kada se kreira nova porudžbina, automatski se pokreće asinkroni proces dostave:

1. **Glavna nit** pokreće dostavu i menja status kroz faze:
   - KREIRANA → U_PRIPREMI (nakon 2s)
   - U_PRIPREMI → SPREMNA (nakon 3s) 
   - SPREMNA → U_DOSTAVI (nakon 2s)

2. **Druga nit** čeka 10 sekundi i označava porudžbinu kao ISPORUCENA

## AOP Logging

Implementiran je AOP aspekt koji loguje sve API pozive:
- Custom `ApiLogger` klasa za formatiranje logova
- `@After` anotacija za logovanje nakon izvršavanja metoda
- Pointcut za sve controller metode
- Loguje timestamp, klasu, metodu i argumente

## Pokretanje aplikacije

### Preduslov
- JDK 17
- Maven 3.6+
- MySQL server (opciono, koristi se H2 za testiranje)

### Korak po korak

1. **Kloniranje repozitorijuma:**
```bash
git clone <repository-url>
cd dostavahrane
```

2. **Pokretanje sa test profilom (H2 database):**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

3. **Pokretanje sa production profilom (MySQL):**
```bash
# Prvo konfigurišite MySQL bazu u application.yml
mvn spring-boot:run
```

Aplikacija će biti dostupna na `http://localhost:8080`

## Testiranje API-ja

### Primer kreiranja korisnika:
```bash
curl -X POST http://localhost:8080/api/korisnici/registracija \
  -H "Content-Type: application/json" \
  -d '{"ime":"Marko","prezime":"Petrović","adresa":"Beograd","kontakt":"123456789","username":"marko","password":"password123"}'
```

### Primer kreiranja restorana:
```bash
curl -X POST http://localhost:8080/api/restorani \
  -H "Content-Type: application/json" \
  -d '{"naziv":"Pizzeria Mario","adresa":"Knez Mihailova 5","tipKuhinje":"italijanska"}'
```

### Primer kreiranja jela:
```bash
curl -X POST http://localhost:8080/api/jela \
  -H "Content-Type: application/json" \
  -d '{"naziv":"Margherita","opis":"Klasična pizza","cena":1200,"restoran":{"id":1}}'
```

### Primer kreiranja porudžbine (demonstrira multithreading):
```bash
curl -X POST http://localhost:8080/api/porudzbine \
  -H "Content-Type: application/json" \
  -d '{"korisnik":{"id":1},"restoran":{"id":1},"ukupanIznos":1200}'
```

## Struktura projekta

```
src/main/java/com/dostavahrane/
├── DostavaHraneApplication.java    # Main aplikaciona klasa
├── aspect/
│   ├── ApiLogger.java              # Custom logger klasa
│   └── LoggingAspect.java          # AOP aspekt za logovanje
├── controller/                     # REST kontroleri
├── entity/                         # JPA entiteti
├── repository/                     # Spring Data JPA repozitorijumi
└── service/                        # Biznis logika servisna klasa
```

## Napomene

- Ova implementacija je proof-of-concept bez složenih validacija
- Korišćene su direktno entity klase umesto DTO klasa (prema zahtevu)
- JSON circular reference problem je rešen @JsonIgnore anotacijama
- Multithreading je demonstriran kroz asinkroni proces dostave
- AOP logging prati sve API pozive sa detaljnim informacijama
