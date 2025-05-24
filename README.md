# Price Comparator

Aplicație Java Spring Boot pentru compararea prețurilor produselor alimentare din mai multe magazine, cu suport pentru reduceri, recomandări și alerte personalizate.

---

## Funcționalități implementate

### Coș optimizat
Returnează cea mai bună combinație de produse din mai multe magazine, în funcție de prețul per unitate și reducerile active.

- Endpoint: `POST /api/basket/optimize`
- Body: listă cu denumirile produselor
- Param: `date=YYYY-MM-DD`

---

### Top reduceri + reduceri noi
- `GET /api/discounts/top?limit=5&date=YYYY-MM-DD`
  - Cele mai mari reduceri active într-o zi
- `GET /api/discounts/new?date=YYYY-MM-DD`
  - Reducerile care încep exact în ziua specificată

---

### Istoric prețuri pentru grafice
Returnează lista de prețuri în timp pentru un produs, filtrabil după magazin/brand/categorie.

- `GET /api/products/price-history`
  - Params: `productName`, `startDate`, `endDate`, `store`, `brand`, `category` (ultimele 3 sunt opționale)

---

### Recomandări per unitate
Compară variantele unui produs și returnează cele mai avantajoase (ex: preț/litru).

- `GET /api/products/recommendations?productName=...&date=...`

---

### Alerte de preț
Permite utilizatorilor să definească un preț țintă și verifică dacă produsul a atins sau a scăzut sub acel prag.

- `POST /api/products/alerts/check?date=...`
- Body: listă de obiecte `PriceAlert` cu `productId` și `targetPrice`

---

## Setup & rulare

### 1. Clonează proiectul

```bash
git clone https://github.com/username/price-comparator.git
cd price-comparator
```

### 2. Asigură-te că ai:

- Java 17+
- Maven
- IntelliJ / VS Code sau alt IDE

### 3. Rulează aplicația

```bash
mvn spring-boot:run
```

Aplicația va porni pe `http://localhost:8080`.

---

## Structura fișierelor CSV

Fișierele de produse și reduceri se află în:
```
src/main/resources/data/
```

Format:
- `lidl_2025-05-08.csv` – conține lista de produse și prețuri
- `lidl_discounts_2025-05-08.csv` – reducerile active în acea zi

Fișierele se încarcă automat pe baza datei trimise în request.

---

## Testare cu Postman

Sunt disponibile toate rutele REST. Se pot testa ușor din Postman sau Swagger (dacă adaugi springdoc).

> Recomand: să folosești tab-ul **Params** din Postman pentru date, ca să eviți newline-uri în query-uri.

---

## Arhitectură

Aplicația urmează o arhitectură curată:

- `controller/` – REST endpoints
- `service/` – logică principală (optimizare, alerte, istorice etc.)
- `model/` – DTO-uri simple
- `util/` – parsare CSV

---

## TODO (posibile extinderi)

- Persistență în DB (ex: SQLite sau PostgreSQL)
- User management și autentificare
- Salvare alerte în background (cu notificări)
- Suport Swagger UI

---

## Autor

Proiect realizat ca parte a Coding Challenge-ului Accesa – Java Internship 2025.
