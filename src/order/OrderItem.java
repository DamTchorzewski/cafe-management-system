package order;

import model.Product;
import exception.InvalidOrderException;
import java.util.Objects;

/**
 * Klasa reprezentująca pojedynczą pozycję w zamówieniu.
 * Łączy produkt z ilością i oblicza wartość częściową pozycji.
 * Zawiera walidację ilości i rzuca własny wyjątek InvalidOrderException w przypadku błędnych danych.
 */


public class OrderItem {
    private Product product;
    private int quantity;
    
    /**
     * Konstruktor klasy OrderItem
     * @param product produkt
     * @param quantity ilość
     * @throws InvalidOrderException gdy ilość jest nieprawidłowa
     */
    public OrderItem(Product product, int quantity) throws InvalidOrderException {
        if (product == null) {
            throw new InvalidOrderException("Produkt nie może być null");
        }
        if (quantity <= 0) {
            throw new InvalidOrderException("Ilość musi być większa od zera");
        }
        this.product = product;
        this.quantity = quantity;
    }
    
    // Gettery i settery
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) throws InvalidOrderException {
        if (product == null) {
            throw new InvalidOrderException("Produkt nie może być null");
        }
        this.product = product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) throws InvalidOrderException {
        if (quantity <= 0) {
            throw new InvalidOrderException("Ilość musi być większa od zera");
        }
        this.quantity = quantity;
    }
    
    /**
     * Oblicza całkowitą cenę pozycji (cena * ilość)
     * @return całkowita cena pozycji
     */
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    /**
     * Zwiększa ilość produktu
     * @param amount ile dodać
     * @throws InvalidOrderException gdy amount jest nieprawidłowy
     */
    public void increaseQuantity(int amount) throws InvalidOrderException {
        if (amount <= 0) {
            throw new InvalidOrderException("Wartość zwiększenia musi być większa od zera");
        }
        this.quantity += amount;
    }
    
    /**
     * Zmniejsza ilość produktu
     * @param amount ile odjąć
     * @throws InvalidOrderException gdy amount jest nieprawidłowy lub wynik byłby <= 0
     */
    public void decreaseQuantity(int amount) throws InvalidOrderException {
        if (amount <= 0) {
            throw new InvalidOrderException("Wartość zmniejszenia musi być większa od zera");
        }
        if (this.quantity - amount <= 0) {
            throw new InvalidOrderException("Ilość nie może być mniejsza lub równa zero");
        }
        this.quantity -= amount;
    }
    
    @Override
    public String toString() {
        return String.format("%dx %s - %.2f zł (razem: %.2f zł)", 
                           quantity, product.getName(), product.getPrice(), getTotalPrice());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(product, orderItem.product);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
