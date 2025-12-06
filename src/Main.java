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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Główna klasa uruchamiająca system CafeControl
 * System obsługi kawiarni z interaktywnym interfejsem użytkownika
 */
public class Main implements IUserInterface, IOrderHandler {
    private List<Order> orderHistory = new ArrayList<>();
    private List<Product> menu = new ArrayList<>();
    private Order currentOrder = null;
    private Scanner scanner;
    
    public Main(Scanner scanner) {
        this.scanner = scanner;
        initializeMenu();
    }
    
    public static void main(String[] args) {
        System.out.println("☕ ════════════════════════════════════════════════");
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
            System.out.println("0. Wyjście");
            System.out.println("═══════════════════════════════════════════");
            System.out.print("Wybierz opcję: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Czyści bufor
                
                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 2:
                        showMenu();
                        break;
                    case 3:
                        showOrderHistory();
                        break;
                    case 4:
                        showStatistics();
                        break;
                    case 0:
                        System.out.println("\n☕ Dziękujemy za skorzystanie z CafeControl!");
                        System.out.println("Do zobaczenia!\n");
                        running = false;
                        break;
                    default:
                        System.out.println("\n[BŁĄD] Nieprawidłowa opcja. Spróbuj ponownie.");
                }
            } catch (Exception e) {
                System.out.println("\n[BŁĄD] Nieprawidłowe dane wejściowe. Wprowadź numer opcji.");
                scanner.nextLine(); // Czyści bufor
            }
        }
    }
    
    /**
     * Przetwarza wejście użytkownika
     * Implementacja metody z interfejsu IUserInterface
     */
    @Override
    public void processUserInput() {
        // Metoda pomocnicza - główna logika w displayMainMenu
        displayMainMenu();
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
            
            System.out.print("Podaj wiek klienta: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            
            Customer customer = new Customer(name, age);
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
                
                switch (choice) {
                    case 1:
                        addProductToOrder();
                        break;
                    case 2:
                        removeProductFromOrder();
                        break;
                    case 3:
                        System.out.println("\n" + currentOrder.getOrderDetails());
                        break;
                    case 4:
                        if (finalizeOrder()) {
                            addingProducts = false;
                        }
                        break;
                    case 0:
                        currentOrder.cancel();
                        System.out.println("\n✓ Zamówienie anulowane");
                        currentOrder = null;
                        addingProducts = false;
                        break;
                    default:
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
            
            // Wybór metody płatności
            System.out.println("\nWybierz metodę płatności:");
            System.out.println("1. Gotówka");
            System.out.println("2. Karta");
            System.out.println("3. Płatność mobilna");
            System.out.println("4. Bon");
            System.out.print("Wybór: ");
            
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
            
            PaymentMethod method;
            switch (paymentChoice) {
                case 1: method = PaymentMethod.CASH; break;
                case 2: method = PaymentMethod.CARD; break;
                case 3: method = PaymentMethod.MOBILE; break;
                case 4: method = PaymentMethod.VOUCHER; break;
                default:
                    System.out.println("\n[BŁĄD] Nieprawidłowa metoda płatności");
                    return false;
            }
            
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
        
        System.out.println("\n☕ NAPOJE:");
        System.out.println("────────────────────────────────────────────");
        for (Product p : menu) { // UPCASTING: Drink/Food traktowane jako Product
            if (p instanceof Drink) { // Sprawdzenie typu przed downcastingiem
                Drink drink = (Drink) p; // DOWNCASTING: Product -> Drink
                System.out.println("  • " + drink.getDescription() + 
                                 " [" + drink.getVolumeInMl() + "ml, " + 
                                 (drink.isHot() ? "gorący" : "zimny") + "]");
            }
        }
        
        System.out.println("\n🍰 JEDZENIE:");
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
        
        for (Order order : orderHistory) {
            System.out.println("\n" + order);
        }
        
        System.out.println("\n────────────────────────────────────────────");
        System.out.println("Łączna liczba zamówień: " + orderHistory.size());
    }
    
    /**
     * Wyświetla statystyki systemu
     */
    private void showStatistics() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          STATYSTYKI SYSTEMU              ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        System.out.println("\n📊 Ogólne:");
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
            
            System.out.println("\n💰 Przychody:");
            System.out.println("  • Łączny przychód: " + String.format("%.2f", totalRevenue) + " zł");
            System.out.println("  • Średnia wartość zamówienia: " + String.format("%.2f", totalRevenue / orderHistory.size()) + " zł");
            System.out.println("  • Łączna liczba sprzedanych produktów: " + totalItems);
        }
        
        System.out.println();
    }
    
    /**
     * Anuluje zamówienie po ID
     * Implementacja metody z interfejsu IOrderHandler
     * @param orderId ID zamówienia do anulowania
     */
    @Override
    public void cancelOrder(int orderId) {
        try {
            for (Order order : orderHistory) {
                if (order.getOrderId() == orderId) {
                    order.cancel();
                    System.out.println("\n✓ Zamówienie #" + orderId + " zostało anulowane");
                    return;
                }
            }
            System.out.println("\n[BŁĄD] Nie znaleziono zamówienia o ID: " + orderId);
        } catch (InvalidOrderException e) {
            System.out.println("\n[BŁĄD] " + e.getMessage());
        }
    }
    
    /**
     * Wyświetla szczegóły zamówienia
     * Implementacja metody z interfejsu IOrderHandler
     * @param order zamówienie do wyświetlenia
     */
    @Override
    public void displayOrderDetails(Order order) {
        if (order == null) {
            System.out.println("\n[BŁĄD] Zamówienie nie istnieje");
            return;
        }
        System.out.println("\n" + order.getOrderDetails());
    }
    
    /**
     * Demonstracja polimorfizmu - różne osoby reprezentowane jako Person
     * Przykład upcasting i downcasting
     */
    private void demonstratePolymorphism() {
        // UPCASTING - Customer i Employee traktowane jako Person
        Person person1 = new Customer("Jan Kowalski", 30); // Upcasting automatyczny
        Person person2 = new Employee("Anna Nowak", 28, "Barista", 4500.0); // Upcasting automatyczny
        
        // Polimorfizm - wywołanie metody abstrakcyjnej zaimplementowanej różnie
        System.out.println(person1.getInfo()); // Wywołuje Customer.getInfo()
        System.out.println(person2.getInfo()); // Wywołuje Employee.getInfo()
        
        // DOWNCASTING - dostęp do specyficznych metod podklas
        if (person1 instanceof Customer) {
            Customer customer = (Customer) person1; // Downcasting z Person do Customer
            System.out.println("ID klienta: " + customer.getCustomerId());
        }
        
        if (person2 instanceof Employee) {
            Employee employee = (Employee) person2; // Downcasting z Person do Employee
            System.out.println("Stanowisko: " + employee.getPosition());
            System.out.println("Pensja: " + employee.getSalary() + " zł");
        }
    }
}
