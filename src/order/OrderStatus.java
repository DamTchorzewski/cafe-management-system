package order;

/**
 * Enum reprezentujący status zamówienia
 */
public enum OrderStatus {
    NEW("Nowe", "Zamówienie zostało utworzone"),
    IN_PROGRESS("W trakcie realizacji", "Zamówienie jest przygotowywane"),
    READY("Gotowe", "Zamówienie jest gotowe do odbioru"),
    COMPLETED("Zakończone", "Zamówienie zostało zrealizowane i odebrane"),
    CANCELLED("Anulowane", "Zamówienie zostało anulowane");
    
    private final String displayName;
    private final String description;
    
    /**
     * Konstruktor enum OrderStatus
     * @param displayName nazwa wyświetlana
     * @param description opis statusu
     */
    OrderStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
