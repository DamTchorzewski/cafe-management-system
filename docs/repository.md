# Repozytorium: opis i tagi

Rzeczy, które ustawia się na GitHubie ręcznie (nie da się ich zapisać w plikach repozytorium). Przegląd projektu: [README](../README.md); wymagania przedmiotu: [requirements.md](requirements.md).

## Opis i tagi (About)

GitHub: strona repozytorium → ⚙ obok „About” → pola poniżej. Opis ma limit 350 znaków, tagi (topics) pisze się małymi literami z myślnikami, najwyżej 20.

**Opis (Description):**

```text
Console café management system in Java: customers, employees, a 16-item menu, orders with statuses, payments (cash, card, mobile, voucher), sorted order history and sales statistics. Object-oriented design with inheritance, interfaces, enums and generics. University team project (Java Programming).
```

**Strona (Website):** puste (aplikacja konsolowa, nie ma strony).

**Tagi (Topics):**

```text
java console-application cafe order-management object-oriented-programming oop inheritance polymorphism interfaces generics enums intellij-idea university-project
```

Z GitHub CLI (`gh`) to samo jedną komendą:

```bash
gh repo edit DamTchorzewski/CafeControl --description "Console café management system in Java: customers, employees, a 16-item menu, orders with statuses, payments (cash, card, mobile, voucher), sorted order history and sales statistics. Object-oriented design with inheritance, interfaces, enums and generics. University team project (Java Programming)." --add-topic java,console-application,cafe,order-management,object-oriented-programming,oop,inheritance,polymorphism,interfaces,generics,enums,intellij-idea,university-project
```

## Ustawienia do włączenia

| Gdzie (GitHub)                                          | Co ustawić                                                                          |
| ------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| Settings → General → Default branch                     | domyślna gałąź to dziś `master`; standard wymaga `main` (zmiana nazwy w Settings → Branches) |
| Settings → Branches (albo Rulesets) → `main`, `develop` | zablokuj force push i usuwanie gałęzi                                               |
