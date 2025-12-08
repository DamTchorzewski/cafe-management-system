package order;

import model.Customer;
import model.Product;
import exception.InvalidOrderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa reprezentująca zamówienie w systemie kawiarni.
 * Implementuje interfejs Comparable<Order> do umożliwienia sortowania zamówień według ID.
 * Zarządza pozycjami zamówienia, oblicza całkowitą wartość i obsługuje statusy zamówienia.
 * Wykorzystuje pole statyczne do generowania unikalnych identyfikatorów zamówień.
 */
public class Order implements Comparable<Order> {
    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
    private Payment payment;
    private static int nextOrderId = 1; // Statyczne pole do automatycznej numeracji
    
    /**
     * Konstruktor klasy Order
     * @param customer klient składający zamówienie
     * @throws InvalidOrderException gdy klient jest null
     */
    public Order(Customer customer) throws InvalidOrderException {
        if (customer == null) {
            throw new InvalidOrderException("Klient nie może być null");
        }
        this.orderId = nextOrderId++;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
        this.payment = null;
    }
    
    /**
     * Konstruktor z określonym ID (do testów)
     * @param orderId ID zamówienia
     * @param customer klient
     * @throws InvalidOrderException gdy klient jest null
     */
    public Order(int orderId, Customer customer) throws InvalidOrderException {
        if (customer == null) {
            throw new InvalidOrderException("Klient nie może być null");
        }
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
        this.payment = null;
        if (orderId >= nextOrderId) {
            nextOrderId = orderId + 1;
        }
    }
    
    // Gettery i settery
    public int getOrderId() {
        return orderId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) throws InvalidOrderException {
        if (customer == null) {
            throw new InvalidOrderException("Klient nie może być null");
        }
        this.customer = customer;
    }
    
    public List<OrderItem> getItems() {
        return new ArrayList<>(items); // Zwraca kopię dla bezpieczeństwa
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) throws InvalidOrderException {
        if (status == null) {
            throw new InvalidOrderException("Status nie może być null");
        }
        this.status = status;
    }
    
    public Payment getPayment() {
        return payment;
    }
    
    /**
     * Statyczna metoda zwracająca liczbę utworzonych zamówień
     * @return liczba zamówień
     */
    public static int getTotalOrders() {
        return nextOrderId - 1;
    }
    
    /**
     * Dodaje produkt do zamówienia
     * @param product produkt do dodania
     * @param quantity ilość
     * @throws InvalidOrderException gdy zamówienie jest już zrealizowane lub anulowane
     */
    public void addProduct(Product product, int quantity) throws InvalidOrderException {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Nie można modyfikować zakończonego lub anulowanego zamówienia");
        }
        
        // Sprawdź czy produkt już istnieje w zamówieniu
        for (OrderItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        
        // Jeśli nie istnieje, dodaj nową pozycję
        OrderItem newItem = new OrderItem(product, quantity);
        items.add(newItem);
    }
    
    /**
     * Usuwa produkt z zamówienia
     * @param product produkt do usunięcia
     * @throws InvalidOrderException gdy produkt nie istnieje lub zamówienie jest zakończone
     */
    public void removeProduct(Product product) throws InvalidOrderException {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Nie można modyfikować zakończonego lub anulowanego zamówienia");
        }
        
        // Ręczne wyszukiwanie i usuwanie (bez lambda)
        boolean removed = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().equals(product)) {
                items.remove(i);
                removed = true;
                break;
            }
        }
        
        if (!removed) {
            throw new InvalidOrderException("Produkt nie znajduje się w zamówieniu");
        }
    }
    
    /**
     * Usuwa pozycję z zamówienia po indeksie
     * @param index indeks pozycji do usunięcia
     * @throws InvalidOrderException gdy indeks jest nieprawidłowy lub zamówienie jest zakończone
     */
    public void removeItem(int index) throws InvalidOrderException {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Nie można modyfikować zakończonego lub anulowanego zamówienia");
        }
        if (index < 0 || index >= items.size()) {
            throw new InvalidOrderException("Nieprawidłowy indeks pozycji");
        }
        items.remove(index);
    }
    
    /**
     * Oblicza całkowitą wartość zamówienia
     * @return suma wartości wszystkich pozycji
     */
    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
    
    /**
     * Tworzy płatność dla zamówienia
     * @throws InvalidOrderException gdy zamówienie jest puste lub już ma płatność
     */
    public void createPayment() throws InvalidOrderException {
        if (items.isEmpty()) {
            throw new InvalidOrderException("Nie można utworzyć płatności dla pustego zamówienia");
        }
        if (payment != null) {
            throw new InvalidOrderException("Płatność już istnieje dla tego zamówienia");
        }
        payment = new Payment(calculateTotal());
    }
    
    /**
     * Tworzy płatność z określoną metodą
     * @param paymentMethod metoda płatności
     * @throws InvalidOrderException gdy zamówienie jest puste lub już ma płatność
     */
    public void createPayment(Payment.PaymentMethod paymentMethod) throws InvalidOrderException {
        if (items.isEmpty()) {
            throw new InvalidOrderException("Nie można utworzyć płatności dla pustego zamówienia");
        }
        if (payment != null) {
            throw new InvalidOrderException("Płatność już istnieje dla tego zamówienia");
        }
        payment = new Payment(calculateTotal(), paymentMethod);
    }
    
    /**
     * Finalizuje zamówienie (ustawia status na COMPLETED)
     * @throws InvalidOrderException gdy zamówienie nie jest opłacone
     */
    public void complete() throws InvalidOrderException {
        if (payment == null || !payment.isPaid()) {
            throw new InvalidOrderException("Zamówienie musi być opłacone przed finalizacją");
        }
        this.status = OrderStatus.COMPLETED;
    }
    
    /**
     * Anuluje zamówienie
     * @throws InvalidOrderException gdy zamówienie jest już zakończone
     */
    public void cancel() throws InvalidOrderException {
        if (status == OrderStatus.COMPLETED) {
            throw new InvalidOrderException("Nie można anulować zakończonego zamówienia");
        }
        this.status = OrderStatus.CANCELLED;
    }
    
    /**
     * Sprawdza czy zamówienie jest puste
     * @return true jeśli brak pozycji
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Zwraca liczbę pozycji w zamówieniu
     * @return liczba pozycji
     */
    public int getItemCount() {
        return items.size();
    }
    
    /**
     * Zwraca całkowitą liczbę produktów (suma ilości)
     * @return suma ilości wszystkich produktów
     */
    public int getTotalQuantity() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }
    
    /**
     * Zwraca szczegółowe informacje o zamówieniu
     * @return String ze szczegółami zamówienia
     */
    public String getOrderDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════════════════╗\n");
        sb.append("║ Zamówienie #");
        sb.append(orderId);
        sb.append("\n");
        sb.append("╚════════════════════════════════════════════════════╝\n");
        sb.append("Klient: ");
        sb.append(customer.getName());
        sb.append(" (ID: ");
        sb.append(customer.getCustomerId());
        sb.append(")\n");
        sb.append("Status: ");
        sb.append(status.getDisplayName());
        sb.append("\n");
        sb.append("\nPozycje zamówienia:\n");
        sb.append("────────────────────────────────────────────────────\n");
        
        if (items.isEmpty()) {
            sb.append("  (brak pozycji)\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                sb.append("  ");
                sb.append(i + 1);
                sb.append(". ");
                sb.append(items.get(i));
                sb.append("\n");
            }
        }
        
        sb.append("────────────────────────────────────────────────────\n");
        sb.append("SUMA: ");
        sb.append(formatPrice(calculateTotal()));
        sb.append(" zł\n");
        
        if (payment != null) {
            sb.append("Płatność: ");
            sb.append(payment.isPaid() ? "Opłacone" : "Nieopłacone");
            sb.append("\n");
            if (payment.getPaymentMethod() != null) {
                sb.append("Metoda: ");
                sb.append(payment.getPaymentMethod().getDisplayName());
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
    
    private String formatPrice(double price) {
        int cents = (int)(price * 100 + 0.5);
        int zloty = cents / 100;
        int grosze = cents % 100;
        return zloty + "." + (grosze < 10 ? "0" : "") + grosze;
    }
    
    @Override
    public String toString() {
        return "Order{ID=" + orderId + ", klient=" + customer.getName() + ", pozycji=" + items.size() + 
               ", status=" + status + ", suma=" + formatPrice(calculateTotal()) + " zł}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
    
    /**
     * Porównuje zamówienia po ID zamówienia (od najmniejszego do największego)
     * Implementacja interfejsu Comparable
     * @param other drugie zamówienie do porównania
     * @return wartość ujemna jeśli to zamówienie ma mniejsze ID, 0 jeśli równe, dodatnia jeśli większe
     */
    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.orderId, other.orderId);
    }
}
