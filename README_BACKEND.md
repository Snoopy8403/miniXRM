# MiniXRM Backend - Spring Boot

Ez a projekt a MiniXRM rendszer backend része, amely Spring Boot keretrendszerre épül. A rendszer partnerek és tevékenységek kezelésére szolgál.

## Futtatási útmutató

### Előfeltételek

- Java 17 vagy újabb
- Maven 3.6+
- Docker (opcionális, teljes rendszer futtatásához)

### Lokális futtatás

1. Klónozd le a repository-t.
2. Navigálj a `backend` könyvtárba.
3. Futtasd a Maven build-et:
   ./mvnw clean install
4. Indítsd el az alkalmazást:
   ./mvnw spring-boot:run
   Az alkalmazás a `http://localhost:8080` címen lesz elérhető.

### Docker segítségével

A teljes rendszer (backend + frontend) futtatásához használd a gyökérkönyvtárban lévő `docker-compose.yml` fájlt:

docker-compose up --build

A backend a `http://localhost:8080` címen, a frontend pedig a `http://localhost:4200` címen lesz elérhető.

### API dokumentáció

Az API dokumentáció elérhető a Swagger UI-n keresztül: `http://localhost:8080/swagger-ui.html`

## Architektúra leírás

A backend egy rétegzett architektúrát követ, amely a következő komponensekből áll:

- **Controller réteg**: REST API végpontok kezelése (`PartnerController`, `ActivityController`, `ReportController`).
- **Service réteg**: Üzleti logika megvalósítása.
- **Repository réteg**: Adatbázis műveletek JPA segítségével.
- **Model réteg**: Adatbázis entitások (`Partner`, `Activity`, `Qualification`) és DTO-k.
- **Config/Exception réteg**: Konfiguráció és kivételkezelés.

Az alkalmazás H2 in-memory adatbázist használ fejlesztési célokra, amely automatikusan inicializálódik az alkalmazás indulásakor.

## Technológiai döntések indoklása

1. **Spring Boot választása**: A Spring Boot gyors fejlesztést tesz lehetővé beépített konfigurációval és auto-konfigurációval, csökkentve a boilerplate kódot. Ez ideális egy prototípus vagy MVP fejlesztéséhez, ahol a gyors iteráció fontos.

2. **JPA + H2 adatbázis**: Az JPA absztrakciót biztosít az adatbázis műveletek felett, míg az H2 in-memory adatbázis egyszerűsíti a fejlesztést és tesztelést anélkül, hogy külső adatbázis telepítése szükséges lenne. Ez gyors prototipizálást tesz lehetővé.

3. **Lombok használata**: A Lombok automatikusan generálja a getter/setter metódusokat és konstruktorokat, csökkentve a repetitív kódot. Ez növeli a kód olvashatóságát és csökkenti a hibák lehetőségét.

4. **Swagger integráció**: Az OpenAPI specifikáció alapján automatikusan generált API dokumentáció megkönnyíti a frontend fejlesztők munkáját és biztosítja az API konzisztenciáját.

5. **Soft delete implementáció**: Az `@SQLDelete` és `@Where` annotációk használatával soft delete-et valósítottunk meg, amely logikailag törli az entitásokat anélkül, hogy fizikailag eltávolítaná őket az adatbázisból. Ez adatbiztonságot biztosít és megkönnyíti az auditálást.

## Kompromisszumok időhiány miatt

1. **Egyszerű autentikáció hiánya**: Az idő rövidsége miatt nem implementáltunk teljes autentikációs és autorizációs rendszert (pl. JWT vagy OAuth). Ez egy production környezetben kritikus lenne, de a prototípus fázisban elfogadható.

2. **Korlátozott validáció**: A bemeneti adatok validációja alapvető szinten maradt, nincs komplex üzleti szabályok érvényesítése. Például a partner adószám formátumának ellenőrzése hiányzik, csak formai ellenőrzés van.

3. **H2 adatbázis használata**: Bár fejlesztésre ideális, production-ban relációs adatbázisra (pl. PostgreSQL) kellene váltani.

4. **Korlátozott tesztelés**: Az idő rövidsége miatt csak alapvető unit tesztek vannak implementálva a service réteghez (PartnerService, ActivityService, ReportService), hiányoznak az integrációs és end-to-end tesztek, amelyek biztosítanák a rendszer teljes stabilitását.

5. **Egyszerűsített hibakezelés**: A kivételkezelés alapvető, nincs részletes error response formázás vagy logging.

## Tesztelés

A projekt tartalmaz 3 unit tesztet a service fájlokhoz:

- **PartnerServiceTest**: Teszteli a partner entitások CRUD műveleteit, beleértve a státusz váltást és soft delete funkcionalitást.
- **ActivityServiceTest**: Ellenőrzi a tevékenységek létrehozását és lekérdezését partnerhez kapcsolódóan.
- **ReportServiceTest**: Validálja a jelentések generálását és az adatok aggregációját.
