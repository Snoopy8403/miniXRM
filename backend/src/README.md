# Mini xRM Backend (Spring Boot)

Ez a projekt egy **egyszerű CRM modul backend implementációja**, amely partnerek és a hozzájuk tartozó tevékenységek kezelésére szolgál.

A rendszer lehetővé teszi:

* Partnerek létrehozását, módosítását és listázását
* Tevékenységek rögzítését partnerekhez
* Riport készítését felelősönként a rögzített tevékenységekről

A projekt célja egy **jól strukturált, tiszta architektúrájú Spring Boot alkalmazás bemutatása**.

---

# Technológiai stack

Backend:

* Java 17
* Spring Boot 3
* Spring Web (REST API)
* Spring Data JPA
* H2 in-memory adatbázis
* Lombok
* OpenAPI / Swagger

---

# Projekt futtatása

## 1. Követelmények

Szükséges:

* Java 17+
* Maven 3.9+

Ellenőrzés:

```
java -version
mvn -v
```

---

## 2. Build

```
mvn clean install
```

---

## 3. Alkalmazás indítása

```
mvn spring-boot:run
```

vagy

```
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

---

# Swagger UI

Az API dokumentáció Swagger segítségével érhető el.

```
http://localhost:8080/swagger-ui/index.html
```

Innen minden endpoint tesztelhető.

---

# H2 adatbázis

A projekt **H2 in-memory adatbázist használ**.

Console elérhetőség:

```
http://localhost:8080/h2-console
```

Beállítások:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (üres)
```

---

# Seed adatok

Alkalmazás indulásakor automatikusan létrejönnek minta adatok:

## Qualifications

* Aktív
* Külföldi
* TOP
* Export

## Partners

| Name              | Status   |
| ----------------- | -------- |
| ACME Ltd          | ACTIVE   |
| Global Trade Kft  | ACTIVE   |
| Local Supplier Bt | INACTIVE |

## Activities

| Subject              | Responsible  | Duration |
| -------------------- | ------------ | -------- |
| Kickoff meeting      | John Doe     | 60       |
| Follow-up call       | Jane Smith   | 30       |
| Export planning      | John Doe     | 90       |
| Supplier negotiation | Mike Johnson | 45       |

Ez biztosítja, hogy a rendszer indulás után azonnal használható legyen.

---

# Architektúra

A projekt **rétegzett architektúrát** követ.

```
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Kiegészítve:

```
DTO
Mapper
Exception handling
```

---

# Package struktúra

```
com.minixrm.backend
│
├── config
│   └── SeedDataLoader
│
├── controller
│   ├── PartnerController
│   ├── ActivityController
│   └── ReportController
│
├── service
│   ├── PartnerService
│   ├── ActivityService
│   └── ReportService
│
├── repository
│   ├── PartnerRepository
│   ├── ActivityRepository
│   └── QualificationRepository
│
├── model
│   ├── entity
│   └── enums
│
├── dto
│   ├── partner
│   ├── activity
│   └── report
│
├── mapper
│
└── exception
```

---

# Entitások

## Partner

Egy üzleti partner.

Attribútumok:

* name
* taxNumber
* address
* status
* qualifications

Kapcsolatok:

```
Partner
  └── ManyToMany → Qualification
  └── OneToMany → Activity
```

---

## Activity

Partnerhez tartozó tevékenység.

Attribútumok:

* subject
* type
* description
* durationMinutes
* responsibleName

Kapcsolat:

```
Activity
   └── ManyToOne → Partner
```

---

## Qualification

Partner címke / minősítés.

Példák:

* TOP
* Export
* Külföldi

---

# Üzleti szabályok

A szabályok a **service rétegben kerülnek implementálásra**.

## 1. Inactive partner

Inactive partnerhez nem lehet tevékenységet rögzíteni.

```
if(partner.getStatus() == INACTIVE)
    throw BusinessException
```

---

## 2. Időtartam validáció

```
durationMinutes > 0
```

---

## 3. Kötelező mezők

* partner
* responsibleName

---

# DTO használat

Az API **nem ad vissza közvetlenül entity-ket**.

```
Entity → Mapper → DTO
```

Előnyök:

* API stabilitás
* adatbázis modell izoláció
* kisebb payload
* biztonság

---

# Mapper réteg

A mapper felel a konverzióért:

```
DTO → Entity
Entity → DTO
```

Például:

```
PartnerMapper
ActivityMapper
```

---

# API endpointok

## Partners

### Partner lista

```
GET /api/partners
```

---

### Partner részletek

```
GET /api/partners/{id}
```

---

### Partner létrehozás

```
POST /api/partners
```

---

### Partner törlés

```
DELETE /api/partners/{id}
```

---

# Activities

### Activity létrehozás

```
POST /api/activities
```

---

### Partner aktivitások

```
GET /api/partners/{partnerId}/activities
```

---

# Report endpoint

A rendszer riportot készít **felelősönként**.

```
GET /api/reports/responsibles
```

Visszatérési példa:

```
[
  {
    "responsibleName": "John Doe",
    "totalMinutes": 150,
    "partnerCount": 2
  }
]
```

---

# Riport lekérdezés

A riport egy **JPA aggregációs query segítségével készül**.

```
SUM(durationMinutes)
COUNT(DISTINCT partner)
GROUP BY responsibleName
```

Ez egy **DTO projection** segítségével történik.

---

# Exception kezelés

A projekt globális exception handler-t használ.

```
@RestControllerAdvice
```

Kezelt hibák:

* NotFoundException
* BusinessException

---

# Adatintegritás

A következő mechanizmusok biztosítják:

* DTO validáció
* Service szintű üzleti szabályok
* JPA kapcsolatok
* repository szintű lekérdezések

---

# Technológiai döntések

## H2 adatbázis

Az alkalmazás H2 adatbázist használ.

Indok:

* gyors setup
* nincs külső dependency
* teszteléshez ideális

Production környezetben:

```
PostgreSQL
```

---

## DTO használat

A DTO réteg biztosítja:

* API stabilitás
* entitás izoláció
* jobb verziókezelhetőség

---

## Service réteg

A business logic elkülönül a controllertől.

```
Controller → HTTP
Service → business logic
Repository → persistence
```

---

# Kompromisszumok (időkorlát miatt)

A projekt a következő egyszerűsítéseket tartalmazza:

* nincs autentikáció
* nincs pagination
* nincs soft delete
* nincs caching

Production rendszerben ezek javasoltak.

---

# Továbbfejlesztési lehetőségek

Lehetséges bővítések:

* pagination
* sorting
* audit log
* soft delete
* optimistic locking
* PostgreSQL adatbázis
* integration tesztek
* Docker deployment

---

# Összegzés

A projekt célja egy **tiszta, skálázható backend architektúra bemutatása**, amely:

* jól elkülönített rétegeket használ
* követi a Spring Boot best practice-eket
* implementálja a feladat üzleti szabályait
* könnyen bővíthető

A rendszer kis méretű, de jól szemlélteti egy **xRM/CRM modul backend alapjait**.
