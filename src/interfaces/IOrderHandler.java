package interfaces;

import order.Order;

/**
 * Interfejs definiujący kontrakt dla obsługi zamówień.
 * Określa metody wymagane do tworzenia, anulowania i przeglądania zamówień.
 */
public interface IOrderHandler {
    void createOrder();
    void cancelOrder(int orderId);
    void displayOrderDetails(Order order);
}
