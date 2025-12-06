package model;

import java.util.Objects;

/**
 * Klasa reprezentująca klienta kawiarni.
 * Dziedziczy po abstrakcyjnej klasie Person i implementuje metodę abstrakcyjną getInfo().
 * Wykorzystuje pole statyczne nextId do automatycznego generowania unikalnych identyfikatorów.
 */
/**
 * Klasa reprezentująca klienta kawiarni
 */
public class Customer extends Person {
    private int customerId;
    private static int nextId = 1;  // Statyczne pole do automatycznej numeracji klientów

    /**
     * Konstruktor klasy Customer
     * @param name imię klienta
     * @param age wiek klienta
     */
    public Customer(String name, int age) {
        super(name, age);
        this.customerId = nextId++;
    }

    /**
     * Konstruktor z określonym ID (do testów)
     * @param name imię klienta
     * @param age wiek klienta
     * @param customerId ID klienta
     */
    public Customer(String name, int age, int customerId) {
        super(name, age);
        this.customerId = customerId;
        if (customerId >= nextId) {
            nextId = customerId + 1;
        }
    }

    // Gettery i settery
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Statyczna metoda zwracająca liczbę utworzonych klientów
     * @return liczba klientów
     */
    public static int getTotalCustomers() {
        return nextId - 1;
    }

    @Override
    public String getInfo() {
        return "Klient: " + name + " (ID: " + customerId + "), wiek: " + age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerId);
    }
}
