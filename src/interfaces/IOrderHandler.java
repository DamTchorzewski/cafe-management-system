package interfaces;

import order.Order;

public interface IOrderHandler {
    void createOrder();
    void cancelOrder(int orderId);
    void displayOrderDetails(Order order);
}
