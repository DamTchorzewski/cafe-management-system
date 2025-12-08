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

3. Uruchom główną klasę `Main.java` (prawy przycisk myszy → Run 'Main.main()')

4. System uruchomi się w konsoli z interaktywnym menu:
   - Tworzenie nowych zamówień
   - Przeglądanie menu kawiarni
   - Historia zamówień
   - Statystyki sprzedaży

## 📊 Aktualny stan projektu

### ✅ Zaimplementowane funkcjonalności:

#### Moduł Model (`model/`)
- ✅ **Person** (abstrakcyjna) - baza dla Customer i Employee
- ✅ **Customer** - zarządzanie klientami z auto-ID
- ✅ **Employee** - zarządzanie pracownikami z auto-ID
- ✅ **Product** (abstrakcyjna) - baza dla Drink i Food
- ✅ **Drink** - napoje z parametrami (temperatura, objętość)
- ✅ **Food** - jedzenie z parametrami (waga, opcja wegetariańska)

#### Moduł Zamówień (`order/`)
- ✅ **Order** - kompleksowe zarządzanie zamówieniami + Comparable
- ✅ **OrderItem** - pozycje zamówienia z walidacją
- ✅ **Payment** - płatności z enum PaymentMethod (Gotówka, Karta, Mobile, Bon)
- ✅ **OrderStatus** (enum) - statusy zamówień (5 stanów)

#### Moduł Narzędzi (`util/`)
- ✅ **ArrayUtils** - metody generyczne do operacji na tablicach

#### Moduł Interfejsów (`interfaces/`)
- ✅ **IUserInterface** - interfejs dla obsługi UI
- ✅ **IOrderHandler** - interfejs dla zarządzania zamówieniami

#### Moduł Wyjątków (`exception/`)
- ✅ **InvalidOrderException** - własny wyjątek użytkownika

#### Interfejs Użytkownika
- ✅ **Main** - implementuje IUserInterface i IOrderHandler
- ✅ Interaktywne menu z obsługą Scanner
- ✅ Tworzenie i zarządzanie zamówieniami
- ✅ Wyświetlanie menu kawiarni (16 produktów)
- ✅ Historia zamówień z sortowaniem (Comparable i Comparator)
- ✅ Statystyki sprzedaży i przychodów
- ✅ Demonstracja polimorfizmu, upcasting i downcasting
- ✅ Demonstracja funkcjonalności: tablice, metody generyczne, tworzenie obiektów


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
│   ├── util/
│   │   └── ArrayUtils.java
│   └── exception/
│       └── InvalidOrderException.java
│
├── README.md
└── .gitignore

```

## 🚀 Plan prac

### **Etap 1 – Struktura projektu (IntelliJ + repozytorium GitHub)** ✅
- Utworzenie głównych pakietów projektu (`model`, `order`, `interfaces`, `util`, `exception`)
- Stworzenie pustych klas i interfejsów
- Inicjalizacja repozytorium GitHub
- Wstępny commit z plikiem `README.md`
- **Status:** UKOŃCZONY

---

### **Etap 2 – Implementacja klas modelu** ✅
- Implementacja klas:  
  `Person`, `Customer`, `Employee`, `Product`, `Drink`, `Food`
- Dodanie konstruktorów, pól, metod i `toString()`
- Wprowadzenie dziedziczenia i hermetyzacji
- **Status:** UKOŃCZONY

---

### **Etap 3 – Moduł zamówień i płatności** ✅
- Utworzenie klas:  
  `Order`, `OrderItem`, `Payment`, `OrderStatus` (enum)
- Implementacja wyjątków użytkownika i obsługi błędów (`try-catch`)
- Dodanie kolekcji (`ArrayList`) i relacji między obiektami
- Implementacja enum `PaymentMethod` dla metod płatności
- **Status:** UKOŃCZONY

---

### **Etap 4 – Interfejs użytkownika i interakcja (Scanner)** ✅
- Implementacja klas:  
  `Main`, `MenuManager`
- Wprowadzenie pętli i komunikatów w konsoli
- Obsługa wejścia użytkownika (Scanner)
- Logika tworzenia zamówień, dodawania produktów i realizacji płatności
- Implementacja interfejsów IUserInterface i IOrderHandler
- **Status:** UKOŃCZONY

---

### **Etap 5 – Dodatkowe funkcje i sortowanie** ✅
- Implementacja interfejsów `Comparable` i `Comparator`
- Wykorzystanie sortowania obiektów na dwa różne sposoby
- Dodanie `enum` (OrderStatus, PaymentMethod)
- Implementacja metody generycznej do sortowania w SortUtils
- Demonstracja upcasting/downcasting
- **Status:** UKOŃCZONY

---

### **Etap 6 – Testy i dokumentacja** ✅
- ✅ Uzupełnienie i finalizacja `README.md`
- ✅ Weryfikacja wszystkich wymagań projektowych
- Testy funkcjonalne wszystkich modułów (TestDemo.java)
- ✅ Dokumentacja i komentarze w kodzie

---

### Uruchomienie głównej aplikacji
```bash
javac src/Main.java
java -cp src Main
```

---

## 📝 Podsumowanie implementacji

### Wszystkie wymagania spełnione ✅

| Wymaganie | Implementacja | Lokalizacja |
|-----------|--------------|-------------|
| Min. 10 klas | 13+ klas | Cały projekt |
| 2 hierarchie dziedziczenia | Person→Customer/Employee<br>Product→Drink/Food | `model/` |
| 2 klasy abstrakcyjne | Person, Product | `model/` |
| 2 metody abstrakcyjne | getInfo(), getDescription() | Person, Product |
| 3 pętle | while, for, foreach | Main.java |
| 3 tablice | String[], PaymentMethod[], String[] | PRODUCT_CATEGORIES, AVAILABLE_PAYMENT_METHODS, ORDER_STATUS_INFO |
| 3 kolekcje | ArrayList×3 | orderHistory, menu, items |
| 2 metody statyczne | getTotalCustomers(), getTotalOrders(), getTotalPayments() | Customer, Order, Payment |
| 2 pola statyczne | nextId, totalPayments | Wiele klas |
| Własny wyjątek | InvalidOrderException | `exception/` |
| Hermetyzacja | Wszystkie pola private/protected | Wszystkie klasy |
| toString() ≥3 | We wszystkich klasach | Cały projekt |
| equals() i hashCode() | We wszystkich klasach | Cały projekt |
| Interfejsy | IUserInterface, IOrderHandler | `interfaces/` + Main |
| Sortowanie | Comparable + Comparator | Order.compareTo() + showOrderHistory() |
| Metoda generyczna | countInArray<T>() | ArrayUtils |
| Upcasting/downcasting | W showMenu() | Main.java |
| Tworzenie >= 2 obiektów | We wszystkich klasach | demonstrateFunctionality() |
| Kolekcje | ArrayList | Cały projekt |
| Enumy | OrderStatus, PaymentMethod | `order/` |
| Interaktywność | Scanner | Main.java |

---

## 👥 Podział zadań w zespole

### **Damian Tchorzewski**
- **Etap 1:** Struktura projektu (pakiety, klasy, repozytorium GitHub) ✅
    - Utworzenie pełnej struktury katalogów i pustych klas
    - Inicjalizacja repozytorium i README.md
- **Etap 6:** Testy końcowe i dokumentacja
    - Weryfikacja działania wszystkich modułów
    - Przygotowanie końcowego README.md i dokumentacji projektu

---

### **Krzysztof Katana**
- **Etap 2:** Implementacja klas modelu ✅
    - Klasy: `Person`, `Customer`, `Employee`, `Product`, `Drink`, `Food`
    - Zastosowanie dziedziczenia i hermetyzacji
    - Implementacja metod `toString()`, `equals()` i `hashCode()`
    - Utworzenie przykładowych obiektów

---

### **Maciej Stefaniuk**
- **Etap 3:** Logika zamówień i obsługa wyjątków ✅
    - Klasy: `Order`, `OrderItem`, `Payment`
    - Enum: `OrderStatus`
    - Zastosowanie kolekcji do przechowywania pozycji zamówienia
    - Obsługa błędów i wyjątek użytkownika `InvalidOrderException`

---

### **Maciej Stefaniuk**
- **Etap 4:** Warstwa interakcji z użytkownikiem
    - Klasy: `Main`, `MenuManager`
    - Obsługa wejścia z klawiatury (Scanner)
    - Implementacja prostego menu, pętli i komunikatów w konsoli
    - Integracja modułów: model + zamówienia

---

### **Eryk Możdżyński**
- **Etap 5:** Dodatkowe funkcjonalności i sortowanie
    - Zaimplementowanie `Comparable` i `Comparator`
    - Dodanie metody generycznej do sortowania obiektów
    - Zastosowanie `enum` w co najmniej jednej klasie
    - Dodanie metod i pól statycznych w wybranych klasach

---

---