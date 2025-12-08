/**
 * Główna klasa aplikacji CafeControl - system zarządzania kawiarnią.
 * Implementuje interfejsy IUserInterface i IOrderHandler, zapewniając
 * interaktywny interfejs konsolowy do obsługi zamówień, menu i klientów.
 * Demonstruje zastosowanie polimorfizmu, dziedziczenia, upcasting i downcasting.
 */
import model.*;
import order.*;
import order.Payment.PaymentMethod;
import exception.InvalidOrderException;
import interfaces.IOrderHandler;
import interfaces.IUserInterface;
import util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main implements IUserInterface, IOrderHandler {
    private List<Order> orderHistory = new ArrayList<>();
    private List<Product> menu = new ArrayList<>();
    private Order currentOrder = null;
    private Scanner scanner;
    
    // Tablice (wymaganie: 3 tablice)
    private static final String[] PRODUCT_CATEGORIES = {"Napoje", "Jedzenie", "Desery"};
    private static final PaymentMethod[] AVAILABLE_PAYMENT_METHODS = {
        PaymentMethod.CASH, PaymentMethod.CARD, PaymentMethod.MOBILE, PaymentMethod.VOUCHER
    };
    private static final String[] ORDER_STATUS_INFO = {
        "Nowe zamówienie - oczekuje na realizację",
        "W trakcie przygotowania",
        "Gotowe do odbioru",
        "Zamówienie zakończone i odebrane",
        "Zamówienie anulowane"
    };
    
    public Main(Scanner scanner) {
        this.scanner = scanner;
        initializeMenu();
    }
    
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════════════");
        System.out.println("   Witamy w systemie CafeControl");
        System.out.println("   System zarządzania kawiarnią");
        System.out.println("════════════════════════════════════════════════\n");
        
        try (Scanner scanner = new Scanner(System.in)) {
            Main app = new Main(scanner);
            app.displayMainMenu();
        }
    }
    
    /**
     * Inicjalizuje menu kawiarni
     */
    private void initializeMenu() {
        menu = getDefaultMenu();
    }
    
    /**
     * Zwraca domyślne menu kawiarni jako listę produktów
     * @return lista produktów zawierająca napoje i jedzenie
     */
    private List<Product> getDefaultMenu() {
        List<Product> defaultMenu = new ArrayList<>();
        
        // Napoje
        defaultMenu.add(new Drink("Espresso", 8.50, true, 30));
        defaultMenu.add(new Drink("Cappuccino", 12.00, true, 200));
        defaultMenu.add(new Drink("Latte", 13.50, true, 250));
        defaultMenu.add(new Drink("Americano", 10.00, true, 150));
        defaultMenu.add(new Drink("Herbata czarna", 7.00, true, 200));
        defaultMenu.add(new Drink("Herbata zielona", 7.50, true, 200));
        defaultMenu.add(new Drink("Lemoniada", 10.00, false, 300));
        defaultMenu.add(new Drink("Sok pomarańczowy", 9.00, false, 250));
        
        // Jedzenie
        defaultMenu.add(new Food("Croissant", 6.50, false, 70));
        defaultMenu.add(new Food("Croissant czekoladowy", 7.50, false, 80));
        defaultMenu.add(new Food("Kanapka z szynką", 15.00, false, 200));
        defaultMenu.add(new Food("Kanapka wegetariańska", 14.00, true, 180));
        defaultMenu.add(new Food("Sałatka grecka", 18.00, true, 250));
        defaultMenu.add(new Food("Ciasto czekoladowe", 15.00, true, 150));
        defaultMenu.add(new Food("Sernik", 14.00, true, 140));
        defaultMenu.add(new Food("Muffin", 8.00, true, 100));
        
        return defaultMenu;
    }
    
    /**
     * Wyświetla główne menu systemu
     * Implementacja metody z interfejsu IUserInterface
     */
    @Override
    public void displayMainMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║          MENU GŁÓWNE                     ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("1. Złóż nowe zamówienie");
            System.out.println("2. Zobacz menu kawiarni");
            System.out.println("3. Historia zamówień");
            System.out.println("4. Statystyki");
            System.out.println("5. Demonstracja funkcjonalności");
            System.out.println("0. Wyjście");
            System.out.println("═══════════════════════════════════════════");
            System.out.print("Wybierz opcję: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Czyści bufor
                
                if (choice == 1) {
                    createOrder();
                } else if (choice == 2) {
                    showMenu();
                } else if (choice == 3) {
                    showOrderHistory();
                } else if (choice == 4) {
                    showStatistics();
                } else if (choice == 5) {
                    demonstrateFunctionality();
                } else if (choice == 0) {
                    System.out.println("\nDziękujemy za skorzystanie z CafeControl!");
                    System.out.println("Do zobaczenia!\n");
                    running = false;
                } else {
                    System.out.println("\n[BŁĄD] Nieprawidłowa opcja. Spróbuj ponownie.");
                }
            } catch (Exception e) {
                System.out.println("\n[BŁĄD] Nieprawidłowe dane wejściowe. Wprowadź numer opcji.");
                scanner.nextLine(); // Czyści bufor
            }
        }
    }
    
    /**
     * Tworzy nowe zamówienie
     * Implementacja metody z interfejsu IOrderHandler
     */
    @Override
    public void createOrder() {
        try {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║       NOWE ZAMÓWIENIE                    ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            // Pobierz dane klienta
            System.out.print("Podaj imię i nazwisko klienta: ");
            String name = scanner.nextLine();
            
            Customer customer = new Customer(name);
            currentOrder = new Order(customer);
            
            System.out.println("\n✓ Zamówienie utworzone dla: " + customer.getInfo());
            
            // Dodawanie produktów do zamówienia
            boolean addingProducts = true;
            while (addingProducts) {
                System.out.println("\n1. Dodaj produkt do zamówienia");
                System.out.println("2. Usuń produkt z zamówienia");
                System.out.println("3. Pokaż aktualne zamówienie");
                System.out.println("4. Finalizuj zamówienie");
                System.out.println("0. Anuluj zamówienie");
                System.out.print("Wybierz opcję: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice == 1) {
                    addProductToOrder();
                } else if (choice == 2) {
                    removeProductFromOrder();
                } else if (choice == 3) {
                    System.out.println("\n" + currentOrder.getOrderDetails());
                } else if (choice == 4) {
                    if (finalizeOrder()) {
                        addingProducts = false;
                    }
                } else if (choice == 0) {
                    currentOrder.cancel();
                    System.out.println("\n✓ Zamówienie anulowane");
                    currentOrder = null;
                    addingProducts = false;
                } else {
                    System.out.println("\n[BŁĄD] Nieprawidłowa opcja");
                }
            }
        } catch (InvalidOrderException e) {
            System.out.println("\n[BŁĄD] " + e.getMessage());
            currentOrder = null;
        } catch (Exception e) {
            System.out.println("\n[BŁĄD] Wystąpił nieoczekiwany błąd: " + e.getMessage());
            scanner.nextLine();
            currentOrder = null;
        }
    }
    
    /**
     * Dodaje produkt do bieżącego zamówienia
     */
    private void addProductToOrder() {
        try {
            System.out.println("\n═══ DOSTĘPNE PRODUKTY ═══");
            for (int i = 0; i < menu.size(); i++) {
                Product p = menu.get(i);
                System.out.println((i + 1) + ". " + p.getDescription());
            }
            
            System.out.print("\nWybierz numer produktu: ");
            int productIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (productIndex < 0 || productIndex >= menu.size()) {
                System.out.println("\n[BŁĄD] Nieprawidłowy numer produktu");
                return;
            }
            
            System.out.print("Podaj ilość: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            
            Product selectedProduct = menu.get(productIndex);
            currentOrder.addProduct(selectedProduct, quantity);
            
            System.out.println("\n✓ Dodano: " + quantity + "x " + selectedProduct.getName());
        } catch (InvalidOrderException e) {
            System.out.println("\n[BŁĄD] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[BŁĄD] Nieprawidłowe dane");
            scanner.nextLine();
        }
    }
    
    /**
     * Usuwa produkt z bieżącego zamówienia
     */
    private void removeProductFromOrder() {
        try {
            if (currentOrder.isEmpty()) {
                System.out.println("\n[INFO] Zamówienie jest puste");
                return;
            }
            
            System.out.println("\n═══ POZYCJE W ZAMÓWIENIU ═══");
            List<OrderItem> items = currentOrder.getItems();
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
            
            System.out.print("\nWybierz numer pozycji do usunięcia: ");
            int itemIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            
            currentOrder.removeItem(itemIndex);
            System.out.println("\n✓ Pozycja usunięta");
        } catch (InvalidOrderException e) {
            System.out.println("\n[BŁĄD] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[BŁĄD] Nieprawidłowy numer pozycji");
            scanner.nextLine();
        }
    }
    
    /**
     * Finalizuje zamówienie (płatność i zakończenie)
     */
    private boolean finalizeOrder() {
        try {
            if (currentOrder.isEmpty()) {
                System.out.println("\n[BŁĄD] Nie można sfinalizować pustego zamówienia");
                return false;
            }
            
            System.out.println("\n" + currentOrder.getOrderDetails());
            
            // Wybór metody płatności (wykorzystanie tablicy)
            System.out.println("\nWybierz metodę płatności:");
            for (int i = 0; i < AVAILABLE_PAYMENT_METHODS.length; i++) {
                System.out.println((i + 1) + ". " + AVAILABLE_PAYMENT_METHODS[i].getDisplayName());
            }
            System.out.print("Wybór: ");
            
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (paymentChoice < 1 || paymentChoice > AVAILABLE_PAYMENT_METHODS.length) {
                System.out.println("\n[BŁĄD] Nieprawidłowa metoda płatności");
                return false;
            }
            
            PaymentMethod method = AVAILABLE_PAYMENT_METHODS[paymentChoice - 1];
            
            // Tworzenie i przetwarzanie płatności
            currentOrder.createPayment(method);
            currentOrder.getPayment().processPayment();
            
            // Zmiana statusu zamówienia
            currentOrder.setStatus(OrderStatus.IN_PROGRESS);
            System.out.println("\n✓ Płatność zrealizowana: " + method.getDisplayName());
            
            currentOrder.setStatus(OrderStatus.READY);
            System.out.println("✓ Zamówienie gotowe do odbioru");
            
            currentOrder.complete();
            System.out.println("✓ Zamówienie zakończone");
            
            // Dodanie do historii
            orderHistory.add(currentOrder);
            
            System.out.println("\n" + currentOrder.getOrderDetails());
            System.out.println("\n✓ Zamówienie #" + currentOrder.getOrderId() + " zostało pomyślnie zrealizowane!");
            
            currentOrder = null;
            return true;
            
        } catch (InvalidOrderException e) {
            System.out.println("\n[BŁĄD] " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("\n[BŁĄD] Wystąpił błąd podczas finalizacji");
            scanner.nextLine();
            return false;
        }
    }
    
    /**
     * Wyświetla menu kawiarni
     * Demonstracja polimorfizmu, upcasting i downcasting
     */
    private void showMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          MENU KAWIARNI                   ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        System.out.println("\nNAPOJE:");
        System.out.println("────────────────────────────────────────────");
        for (Product p : menu) { // UPCASTING: Drink/Food traktowane jako Product
            if (p instanceof Drink) { // Sprawdzenie typu przed downcastingiem
                Drink drink = (Drink) p; // DOWNCASTING: Product -> Drink
                System.out.println("  • " + drink.getDescription() + 
                                 " [" + drink.getVolumeInMl() + "ml, " + 
                                 (drink.isHot() ? "gorący" : "zimny") + "]");
            }
        }
        
        System.out.println("\nJEDZENIE:");
        System.out.println("────────────────────────────────────────────");
        for (Product p : menu) { // UPCASTING
            if (p instanceof Food) { // Sprawdzenie typu przed downcastingiem
                Food food = (Food) p; // DOWNCASTING: Product -> Food
                System.out.println("  • " + food.getDescription() + 
                                 " [" + food.getWeightInGrams() + "g" + 
                                 (food.isVegetarian() ? ", WEGE" : "") + "]");
            }
        }
        System.out.println();
    }
    
    /**
     * Wyświetla historię zamówień
     */
    private void showOrderHistory() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       HISTORIA ZAMÓWIEŃ                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        if (orderHistory.isEmpty()) {
            System.out.println("\nBrak zamówień w historii");
            return;
        }
        
        System.out.println("\nWybierz sposób sortowania:");
        System.out.println("1. Po ID zamówienia (rosnąco)");
        System.out.println("2. Po wartości zamówienia (rosnąco)");
        System.out.print("Wybór: ");
        
        try {
            int sortChoice = scanner.nextInt();
            scanner.nextLine();
            
            List<Order> sortedOrders = new ArrayList<>(orderHistory);
            
            if (sortChoice == 1) {
                // Sortowanie za pomocą Comparable (compareTo)
                Collections.sort(sortedOrders);
                System.out.println("\n[Sortowanie: według ID zamówienia]");
            } else if (sortChoice == 2) {
                // Sortowanie za pomocą Comparator
                Collections.sort(sortedOrders, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return Double.compare(o1.calculateTotal(), o2.calculateTotal());
                    }
                });
                System.out.println("\n[Sortowanie: według wartości zamówienia]");
            } else {
                // Domyślnie po ID
                Collections.sort(sortedOrders);
                System.out.println("\n[Sortowanie domyślne: według ID zamówienia]");
            }
            
            for (Order order : sortedOrders) {
                System.out.println("\n" + order);
            }
            
            System.out.println("\n────────────────────────────────────────────");
            System.out.println("Łączna liczba zamówień: " + orderHistory.size());
            
        } catch (Exception e) {
            System.out.println("\n[BŁĄD] Nieprawidłowe dane");
            scanner.nextLine();
        }
    }
    
    /**
     * Wyświetla statystyki systemu
     */
    private void showStatistics() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          STATYSTYKI SYSTEMU              ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        System.out.println("\nOgólne:");
        System.out.println("  • Łączna liczba zamówień: " + Order.getTotalOrders());
        System.out.println("  • Zamówienia w historii: " + orderHistory.size());
        System.out.println("  • Liczba klientów: " + Customer.getTotalCustomers());
        System.out.println("  • Zrealizowane płatności: " + Payment.getTotalPayments());
        
        if (!orderHistory.isEmpty()) {
            double totalRevenue = 0;
            int totalItems = 0;
            
            for (Order order : orderHistory) {
                totalRevenue += order.calculateTotal();
                totalItems += order.getTotalQuantity();
            }
            
            System.out.println("\nPrzychody:");
            System.out.println("  • Łączny przychód: " + formatPrice(totalRevenue) + " zł");
            System.out.println("  • Średnia wartość zamówienia: " + formatPrice(totalRevenue / orderHistory.size()) + " zł");
            System.out.println("  • Łączna liczba sprzedanych produktów: " + totalItems);
        }
        
        System.out.println();
    }
    
    private String formatPrice(double price) {
        int cents = (int)(price * 100 + 0.5);
        int zloty = cents / 100;
        int grosze = cents % 100;
        return zloty + "." + (grosze < 10 ? "0" : "") + grosze;
    }
    
    /**
     * Demonstracja funkcjonalności - tworzenie obiektów, użycie tablic i metod generycznych
     */
    private void demonstrateFunctionality() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     DEMONSTRACJA FUNKCJONALNOŚCI         ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        // Demonstracja tworzenia >= 2 obiektów każdej klasy
        System.out.println("\n=== TWORZENIE OBIEKTÓW ===");
        
        // 2 obiekty Customer
        Customer customer1 = new Customer("Jan Kowalski");
        Customer customer2 = new Customer("Anna Nowak");
        System.out.println("\nKlienci:");
        System.out.println("  " + customer1.getInfo());
        System.out.println("  " + customer2.getInfo());
        
        // 2 obiekty Employee
        Employee emp1 = new Employee("Piotr Wiśniewski", "Barista", 4500.0);
        Employee emp2 = new Employee("Maria Kowalska", "Manager", 6000.0);
        System.out.println("\nPracownicy:");
        System.out.println("  " + emp1.getInfo());
        System.out.println("  " + emp2.getInfo());
        
        // 2 obiekty Drink
        Drink drink1 = new Drink("Espresso", 8.50, true, 30);
        Drink drink2 = new Drink("Latte", 12.00, true, 250);
        System.out.println("\nNapoje:");
        System.out.println("  " + drink1.getDescription());
        System.out.println("  " + drink2.getDescription());
        
        // 2 obiekty Food
        Food food1 = new Food("Croissant", 6.50, false, 70);
        Food food2 = new Food("Sałatka grecka", 18.00, true, 250);
        System.out.println("\nJedzenie:");
        System.out.println("  " + food1.getDescription());
        System.out.println("  " + food2.getDescription());
        
        // Demonstracja użycia tablic
        System.out.println("\n=== UŻYCIE TABLIC ===");
        
        System.out.println("\nKategorie produktów:");
        for (int i = 0; i < PRODUCT_CATEGORIES.length; i++) {
            System.out.println("  " + (i + 1) + ". " + PRODUCT_CATEGORIES[i]);
        }
        
        System.out.println("\nDostępne metody płatności:");
        for (int i = 0; i < AVAILABLE_PAYMENT_METHODS.length; i++) {
            System.out.println("  " + (i + 1) + ". " + AVAILABLE_PAYMENT_METHODS[i].getDisplayName());
        }
        
        System.out.println("\nOpisy statusów zamówień:");
        for (int i = 0; i < ORDER_STATUS_INFO.length; i++) {
            System.out.println("  " + (i + 1) + ". " + ORDER_STATUS_INFO[i]);
        }
        
        // Demonstracja metod generycznych
        System.out.println("\n=== UŻYCIE METODY GENERYCZNEJ ===");
        
        int count = ArrayUtils.countInArray(AVAILABLE_PAYMENT_METHODS, PaymentMethod.CASH);
        System.out.println("\nLiczenie wystąpień 'CASH' w tablicy AVAILABLE_PAYMENT_METHODS:");
        System.out.println("  Liczba wystąpień: " + count);
        
        int categoryCount = ArrayUtils.countInArray(PRODUCT_CATEGORIES, "Napoje");
        System.out.println("\nLiczenie wystąpień 'Napoje' w tablicy PRODUCT_CATEGORIES:");
        System.out.println("  Liczba wystąpień: " + categoryCount);
        
        System.out.println("\n=== KONIEC DEMONSTRACJI ===\n");
    }
}
