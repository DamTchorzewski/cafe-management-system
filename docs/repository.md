# Repozytorium: opis i tagi

Rzeczy, które ustawia się na GitHubie ręcznie (nie da się ich zapisać w plikach repozytorium). Przegląd projektu: [README](../README.md); wymagania przedmiotu: [requirements.md](requirements.md).

## Nazwa repozytorium: `cafe-management-system`

Standard nazw repozytoriów: `reops/standards/repo-names.md`. Repozytorium nazywało się wcześniej `CafeControl`; od 2026-11-20 nazywa się `cafe-management-system` (nazwa mówi, czym jest projekt: fraza szukana na GitHubie), a domyślna gałąź `master` nazywa się `main`. GitHub przekierowuje stary adres repozytorium i starą gałąź. Nazwa produktu w kodzie i w menu programu nadal brzmi „CafeControl”.

## Opis i tagi (About)

GitHub: strona repozytorium → ⚙ obok „About” → pola poniżej. Opis ma limit 350 znaków, tagi (topics) pisze się małymi literami z myślnikami, najwyżej 20.

**Opis (Description):**

```text
Java console application for cafe management: customers, employees, menu, orders, payments and sales statistics.
```

**Strona (Website):** puste (aplikacja konsolowa, nie ma strony).

**Tagi (Topics):**

```text
java console-application cafe-management-system order-management object-oriented-programming oop inheritance polymorphism interfaces generics enums intellij-idea
```

Z GitHub CLI (`gh`) to samo jedną komendą:

```bash
gh repo edit DamTchorzewski/cafe-management-system --description "Java console application for cafe management: customers, employees, menu, orders, payments and sales statistics." --add-topic java --add-topic console-application --add-topic cafe-management-system --add-topic order-management --add-topic object-oriented-programming --add-topic oop --add-topic inheritance --add-topic polymorphism --add-topic interfaces --add-topic generics --add-topic enums --add-topic intellij-idea --add-topic
```

## Ustawienia do włączenia

| Gdzie (GitHub) | Co ustawić |
| ------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| Settings → General → Default branch | `main` (gałąź `master` przemianowana 2026-11-20) |
| Settings → Branches (albo Rulesets) → `main`, `develop` | zablokuj force push i usuwanie gałęzi |
