package order;

import exception.InvalidOrderException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Klasa reprezentująca płatność za zamówienie
 */
public class Payment {
    private double amount;
    private boolean isPaid;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDate;
    private static int totalPayments = 0; // Statyczne pole do liczenia płatności
    
    /**
     * Enum reprezentujący metodę płatności
     */
    public enum PaymentMethod {
        CASH("Gotówka"),
        CARD("Karta"),
        MOBILE("Płatność mobilna"),
        VOUCHER("Bon");
        
        private final String displayName;
        
        PaymentMethod(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
    
    /**
     * Konstruktor klasy Payment
     * @param amount kwota do zapłaty
     * @throws InvalidOrderException gdy kwota jest nieprawidłowa
     */
    public Payment(double amount) throws InvalidOrderException {
        if (amount < 0) {
            throw new InvalidOrderException("Kwota płatności nie może być ujemna");
        }
        this.amount = amount;
        this.isPaid = false;
        this.paymentMethod = null;
        this.paymentDate = null;
    }
    
    /**
     * Konstruktor z metodą płatności
     * @param amount kwota do zapłaty
     * @param paymentMethod metoda płatności
     * @throws InvalidOrderException gdy kwota jest nieprawidłowa
     */
    public Payment(double amount, PaymentMethod paymentMethod) throws InvalidOrderException {
        if (amount < 0) {
            throw new InvalidOrderException("Kwota płatności nie może być ujemna");
        }
        this.amount = amount;
        this.isPaid = false;
        this.paymentMethod = paymentMethod;
        this.paymentDate = null;
    }
    
    // Gettery i settery
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) throws InvalidOrderException {
        if (amount < 0) {
            throw new InvalidOrderException("Kwota płatności nie może być ujemna");
        }
        this.amount = amount;
    }
    
    public boolean isPaid() {
        return isPaid;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    /**
     * Statyczna metoda zwracająca liczbę zrealizowanych płatności
     * @return liczba płatności
     */
    public static int getTotalPayments() {
        return totalPayments;
    }
    
    /**
     * Przetwarza płatność
     * @param paymentMethod metoda płatności
     * @throws InvalidOrderException gdy płatność już została zrealizowana
     */
    public void processPayment(PaymentMethod paymentMethod) throws InvalidOrderException {
        if (isPaid) {
            throw new InvalidOrderException("Płatność została już zrealizowana");
        }
        if (paymentMethod == null) {
            throw new InvalidOrderException("Metoda płatności nie może być null");
        }
        this.isPaid = true;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();
        totalPayments++;
    }
    
    /**
     * Przetwarza płatność (używa już ustawionej metody)
     * @throws InvalidOrderException gdy płatność już została zrealizowana lub nie ustawiono metody
     */
    public void processPayment() throws InvalidOrderException {
        if (isPaid) {
            throw new InvalidOrderException("Płatność została już zrealizowana");
        }
        if (paymentMethod == null) {
            throw new InvalidOrderException("Nie wybrano metody płatności");
        }
        this.isPaid = true;
        this.paymentDate = LocalDateTime.now();
        totalPayments++;
    }
    
    /**
     * Anuluje płatność
     * @throws InvalidOrderException gdy płatność nie była zrealizowana
     */
    public void cancelPayment() throws InvalidOrderException {
        if (!isPaid) {
            throw new InvalidOrderException("Nie można anulować niezrealizowanej płatności");
        }
        this.isPaid = false;
        this.paymentDate = null;
        totalPayments--;
    }
    
    /**
     * Zwraca sformatowaną datę płatności
     * @return data płatności jako String
     */
    public String getFormattedPaymentDate() {
        if (paymentDate == null) {
            return "Brak";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return paymentDate.format(formatter);
    }
    
    @Override
    public String toString() {
        String status = isPaid ? "Opłacone" : "Nieopłacone";
        String method = paymentMethod != null ? paymentMethod.getDisplayName() : "Nie wybrano";
        return String.format("Payment{kwota=%.2f zł, status=%s, metoda=%s, data=%s}", 
                           amount, status, method, getFormattedPaymentDate());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 
            && isPaid == payment.isPaid 
            && paymentMethod == payment.paymentMethod;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(amount, isPaid, paymentMethod);
    }
}
