package model;

import java.util.Objects;

/**
 * Abstrakcyjna klasa bazowa reprezentująca produkt w menu kawiarni.
 * Stanowi podstawę hierarchii dziedziczenia dla klas Drink i Food.
 * Definiuje wspólne właściwości i abstrakcyjną metodę getDescription().
 */

public abstract class Product {
    protected String name;
    protected double price;

    /**
     * Konstruktor klasy Product
     * @param name nazwa produktu
     * @param price cena produktu
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Gettery i settery (hermetyzacja)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Metoda abstrakcyjna zwracająca szczegółowy opis produktu
     * @return String ze szczegółowym opisem produktu
     */
    public abstract String getDescription();

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
