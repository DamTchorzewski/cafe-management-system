# ☕ CafeControl – System obsługi kawiarni (Java)

## 🎯 Opis projektu
**CafeControl** to interaktywny system obsługi kawiarni napisany w języku **Java**, umożliwiający zarządzanie klientami, zamówieniami, menu, płatnościami oraz personelem.  
Projekt realizowany w ramach zajęć **Programowanie w Javie**.

System działa w konsoli (CLI) i komunikuje się z użytkownikiem poprzez dane wprowadzane z klawiatury za pomocą klasy `Scanner`.

---

## 👥 Autorzy


Zespół projektowy  – Programowanie w Javie
- Eryk Możdżyński 68319
- Maciej Stefaniuk 73679
- Krzysztof Katana 71508
- Damian Tchorzewski 72206

---

## ⚙️ Procedura uruchomienia

### Wymagania wstępne
- **Java JDK 21** lub nowsza
- **IntelliJ IDEA** (lub inne IDE obsługujące Javę)
- System operacyjny: Windows / Linux / macOS

### Instrukcja uruchomienia
1. Sklonuj repozytorium lub pobierz projekt:
   ```bash
   git clone https://github.com/DamTchorzewski/CafeControl.git
   ```
2. Otwórz projekt w IntelliJ IDEA.

3. Uruchom główną klasę Main.java

## 🧩 Założenia i wymagania projektowe

Projekt musi spełniać poniższe wymagania:

Wymagania konieczne:
- Interaktywność (wprowadzanie danych przez użytkownika – Scanner)
- Każda klasa w osobnym pliku
- Użycie tylko składni Javy poznanej na zajęciach
- Projekt grupowy (4 osoby)

Wymagania oceniane:
- Min. 10 klas
- Co najmniej 2 hierarchie dziedziczenia
- 2 klasy abstrakcyjne i 2 metody abstrakcyjne
- 3 pętle i 3 tablice
- Zastosowanie upcastingu i downcastingu
- 2 metody i 2 pola statyczne
- Własny wyjątek użytkownika + obsługa wyjątków
- Hermetyzacja (pola prywatne, gettery/settery)
- Nadpisanie metod toString() (≥3), equals() i hashCode()
- Interfejs użytkownika (definicja i implementacja)
- Sortowanie obiektów (Comparable, Comparator)
- Metoda generyczna
- Kolekcje (np. ArrayList, HashMap)
- Enum (np. typ napoju, status zamówienia)
- Czytelne komunikaty w konsoli

## 🧱 Struktura projektu (planowana)
```bash
CafeControl/
│
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── Person.java
│   │   ├── Customer.java
│   │   ├── Employee.java
│   │   ├── Product.java
│   │   ├── Drink.java
│   │   └── Food.java
│   ├── order/
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Payment.java
│   │   └── OrderStatus.java (enum)
│   ├── interfaces/
│   │   ├── IUserInterface.java
│   │   └── IOrderHandler.java
│   ├── exception/
│   │   └── InvalidOrderException.java
│   └── util/
│       ├── MenuManager.java
│       └── SortUtils.java
│
├── README.md
└── .gitignore

```

## 🚀 Plan prac

### **Etap 1 – Struktura projektu (IntelliJ + repozytorium GitHub)**
- Utworzenie głównych pakietów projektu (`model`, `order`, `interfaces`, `util`, `exception`)
- Stworzenie pustych klas i interfejsów
- Inicjalizacja repozytorium GitHub
- Wstępny commit z plikiem `README.md`
- *ukończony

---

### **Etap 2 – Implementacja klas modelu**
- Implementacja klas:  
  `Person`, `Customer`, `Employee`, `Product`, `Drink`, `Food`
- Dodanie konstruktorów, pól, metod i `toString()`
- Wprowadzenie dziedziczenia i hermetyzacji

---

### **Etap 3 – Moduł zamówień i płatności**
- Utworzenie klas:  
  `Order`, `OrderItem`, `Payment`
- Implementacja wyjątków użytkownika i obsługi błędów (`try-catch`)
- Dodanie kolekcji i relacji między obiektami

---

### **Etap 4 – Interfejs użytkownika i interakcja (Scanner)**
- Implementacja klas:  
  `Main`, `MenuManager`
- Wprowadzenie pętli i komunikatów w konsoli
- Obsługa wejścia użytkownika (Scanner)
- Logika tworzenia zamówień, dodawania produktów i realizacji płatności

---

### **Etap 5 – Dodatkowe funkcje i sortowanie**
- Implementacja interfejsów `Comparable` i `Comparator`
- Wykorzystanie sortowania obiektów na dwa różne sposoby
- Dodanie `enum` (np. typy napojów, status zamówienia)
- Implementacja metody generycznej do sortowania

---

### **Etap 6 – Testy i dokumentacja**
- Utworzenie przykładowych obiektów i testów funkcjonalnych
- Sprawdzenie poprawności działania wszystkich funkcji
- Uzupełnienie i finalizacja `README.md`
- Ostateczny commit i wersja końcowa projektu


## 👥 Podział zadań w zespole

### **Damian Tchorzewski 72206**
- **Etap 1:** Struktura projektu (pakiety, klasy, repozytorium GitHub)
    - Utworzenie pełnej struktury katalogów i pustych klas
    - Inicjalizacja repozytorium i README.md
- **Etap 6:** Testy końcowe i dokumentacja
    - Weryfikacja działania wszystkich modułów
    - Przygotowanie końcowego README.md i dokumentacji projektu

---

### **Członek zespołu – Model danych**
- **Etap 2:** Implementacja klas modelu
    - Klasy: `Person`, `Customer`, `Employee`, `Product`, `Drink`, `Food`
    - Zastosowanie dziedziczenia i hermetyzacji
    - Implementacja metod `toString()`, `equals()` i `hashCode()`
    - Utworzenie przykładowych obiektów

---

### **Członek zespołu – Moduł zamówień i płatności**
- **Etap 3:** Logika zamówień i obsługa wyjątków
    - Klasy: `Order`, `OrderItem`, `Payment`
    - Enum: `OrderStatus`
    - Zastosowanie kolekcji do przechowywania pozycji zamówienia
    - Obsługa błędów i wyjątek użytkownika `InvalidOrderException`

---

### **Członek zespołu – Interfejs użytkownika**
- **Etap 4:** Warstwa interakcji z użytkownikiem
    - Klasy: `Main`, `MenuManager`
    - Obsługa wejścia z klawiatury (Scanner)
    - Implementacja prostego menu, pętli i komunikatów w konsoli
    - Integracja modułów: model + zamówienia

---

### **Członek zespołu – Funkcje rozszerzone**
- **Etap 5:** Dodatkowe funkcjonalności i sortowanie
    - Zaimplementowanie `Comparable` i `Comparator`
    - Dodanie metody generycznej do sortowania obiektów
    - Zastosowanie `enum` w co najmniej jednej klasie
    - Dodanie metod i pól statycznych w wybranych klasach

---