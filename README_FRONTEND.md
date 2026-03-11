# MiniXRM Frontend - Angular

Ez a projekt a MiniXRM rendszer frontend része, amely Angular keretrendszerre épül. A rendszer partnerek és tevékenységek kezelésére szolgáló felhasználói felületet biztosít.

## Futtatási útmutató

### Előfeltételek

- Node.js 20+
- npm vagy yarn
- Angular CLI (opcionális, de ajánlott)
- Docker (opcionális, teljes rendszer futtatásához)

### Lokális futtatás

1. Klónozd le a repository-t.
2. Navigálj a `frontend` könyvtárba.
3. Telepítsd a függőségeket:
   ```
   npm install
   ```
4. Indítsd el a fejlesztési szervert:
   ```
   npm start
   ```
   Az alkalmazás a `http://localhost:4200` címen lesz elérhető.
   !!!(Ellenőrizd, hogy a backend is fut a `http://localhost:8080` címen.)!!!

### Buildelés

Production build létrehozásához:

npm run build

### Docker segítségével

A teljes rendszer (backend + frontend) futtatásához használd a gyökérkönyvtárban lévő `docker-compose.yml` fájlt:

docker-compose up --build

A frontend a `http://localhost:4200` címen lesz elérhető.

## Architektúra leírás

A frontend egy komponens-alapú architektúrát követ, amely a következő modulokból áll:

- **Core modul**: Közös szolgáltatások (PartnerService, ActivityService, ReportService) az API kommunikációhoz.
- **Features modul**: Funkcionális modulok (partners, reports) lazy loading-gal betöltve.
- **Shared modul**: Megosztott direktívák és komponensek (pl. tax-number-format directive).
- **Models**: TypeScript interfészek az adatok típusának definiálására (Partner, Activity, Report).
- **Layout**: Navigációs komponensek (navbar).

Az alkalmazás standalone komponenseket használ, amelyek csökkentik a boilerplate kódot és javítják a teljesítményt. Az útválasztás lazy loading-ot alkalmaz a bundle méret optimalizálására.

## Technológiai döntések indoklása

1. **Angular 19 választása**: A legújabb Angular verzió használata biztosítja a legjobb teljesítményt, új funkciókat (pl. standalone components) és hosszú távú támogatottságot. Ez jövőbiztos megoldást nyújt a frontend fejlesztéshez.

2. **Angular Material használata**: A Material Design komponenskönyvtár konzisztens és professzionális UI-t biztosít minimális egyedi styling-gal. Ez gyorsítja a fejlesztést és javítja a felhasználói élményt.

3. **Standalone komponensek**: Az új Angular architektúra használata csökkenteni a modulok komplexitását.

4. **RxJS integráció**: Reaktív programozás az aszinkron műveletek kezelésére, amely robusztus és könnyen tesztelhető kódot eredményez. Az Observable-ok használata konzisztens adatfolyamot biztosít az API hívásoknál.

5. **Lazy loading útválasztás**: A feature modulok igény szerinti betöltése csökkenti az inicial bundle méretet és javítja az alkalmazás indulási teljesítményét.

## Kompromisszumok időhiány miatt

1. **Egyszerű error handling**: Az idő rövidsége miatt nincs globális error handling implementálva, csak alapvető try-catch blokkok vannak. Ez production-ban problémás lehet, ahol részletes hibaüzenetek és fallback UI szükséges.

2. **Korlátozott validáció**: Az űrlapok validációja alapvető, nincs komplex üzleti szabályok érvényesítése vagy dinamikus validáció. Például a partner adatok formátumának ellenőrzése hiányzik.

3. **Hiányzó autentikáció**: Nincs frontend oldali autentikációs logika implementálva, feltételezve hogy a backend kezeli. Ez biztonság hiányt okozhat.

4. **Egyszerűsített state management**: Nincs központi state management (pl. NgRx), csak szolgáltatásokon keresztül történik az adatkezelés. Ez nagyobb alkalmazásoknál problémás lehet.

5. **Minimális design**: Az időhiány miatt csak alapvető elemek kerültek megjelenítésre, lehetne még formálni-igazítani rajta.

6. **Partner szűrés frontenden**: Az időhiány miatt a partnerek listájának a szűrését frontenden oldottam meg, de sokkal jobb megoldás lenne, ha a backenden történne a szűrés. Jelenleg a kevés adat miatt ez még nem számottevő, de sok partner esetén gyorsabb lenne, ha csak azokat az adatokat adná vissza a végpont amire valóban szüksége van.
