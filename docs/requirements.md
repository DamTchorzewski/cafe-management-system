# Wymagania projektu i ich realizacja

Założenia przedmiotu *Programowanie w Javie* i miejsce, w którym każde z nich spełnia kod. Przegląd projektu: [README](../README.md); opis i tagi repozytorium: [repository.md](repository.md). Stan na 2025-12-08 (ostatnia zmiana kodu).

## Wymagania konieczne

- Interaktywność: dane wprowadzane z klawiatury klasą `Scanner`.
- Każda klasa w osobnym pliku.
- Tylko składnia Javy poznana na zajęciach.
- Projekt grupowy (4 osoby).

## Wymagania oceniane

| Wymaganie                          | Realizacja                                                              | Gdzie                                                             |
| ---------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------- |
| min. 10 klas                       | 13 klas, 2 interfejsy, 2 enumy                                          | cały projekt                                                      |
| 2 hierarchie dziedziczenia         | `Person` → `Customer`, `Employee`; `Product` → `Drink`, `Food`          | `src/model/`                                                      |
| 2 klasy i 2 metody abstrakcyjne    | `Person`, `Product`; `getInfo()`, `getDescription()`                    | `src/model/`                                                      |
| 3 pętle                            | `while`, `for`, `foreach`                                               | `Main.java`                                                       |
| 3 tablice                          | `String[]`, `PaymentMethod[]`, `String[]`                               | `PRODUCT_CATEGORIES`, `AVAILABLE_PAYMENT_METHODS`, `ORDER_STATUS_INFO` |
| kolekcje                           | `ArrayList` (historia zamówień, menu, pozycje zamówienia)               | `Main.java`, `Order.java`                                         |
| 2 metody i 2 pola statyczne        | `getTotalCustomers()`, `getTotalOrders()`, `getTotalPayments()`; `nextId`, `totalPayments` | `Customer`, `Order`, `Payment`                  |
| własny wyjątek i jego obsługa      | `InvalidOrderException`, `try`-`catch`                                  | `src/exception/`, `Order`, `Payment`                              |
| hermetyzacja                       | pola `private` / `protected`, gettery i settery                         | wszystkie klasy                                                   |
| `toString()` (≥ 3), `equals()`, `hashCode()` | nadpisane w klasach modelu i zamówień                         | cały projekt                                                      |
| interfejs użytkownika              | `IUserInterface`, `IOrderHandler`, implementowane przez `Main`          | `src/interfaces/`, `Main.java`                                    |
| sortowanie obiektów                | `Comparable` (`Order.compareTo()`) i `Comparator` (po wartości zamówienia) | `Order.java`, `Main.java`, `SortUtils`                         |
| metoda generyczna                  | `countInArray<T>()`                                                     | `ArrayUtils`                                                      |
| upcasting i downcasting            | przy wyświetlaniu menu                                                  | `Main.showMenu()`                                                 |
| enum                               | `OrderStatus` (5 stanów), `PaymentMethod` (4 metody)                    | `src/order/`                                                      |
| czytelne komunikaty w konsoli      | menu z numerami, komunikaty o błędach                                   | `Main.java`                                                       |

## Plan prac (wszystkie etapy ukończone)

1. Struktura projektu: pakiety `model`, `order`, `interfaces`, `util`, `exception`, repozytorium GitHub.
2. Klasy modelu, dziedziczenie i hermetyzacja.
3. Zamówienia i płatności, wyjątek użytkownika, kolekcje.
4. Interfejs użytkownika: `Scanner`, menu, pętle, implementacja interfejsów.
5. Sortowanie (`Comparable`, `Comparator`), enumy, metoda generyczna, upcasting i downcasting.
6. Weryfikacja wymagań, dokumentacja i komentarze w kodzie.

Podział pracy w zespole jest w [README](../README.md#team).
