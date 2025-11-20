package model;

import java.util.Objects;

/**
 * Klasa reprezentująca pracownika kawiarni
 */
public class Employee extends Person {
    private int employeeId;
    private String position;
    private static int nextId = 1000;  // Statyczne pole do automatycznej numeracji pracowników

    /**
     * Konstruktor klasy Employee
     * @param name imię pracownika
     * @param age wiek pracownika
     * @param position stanowisko pracownika
     */
    public Employee(String name, int age, String position) {
        super(name, age);
        this.employeeId = nextId++;
        this.position = position;
    }

    /**
     * Konstruktor z określonym ID (do testów)
     * @param name imię pracownika
     * @param age wiek pracownika
     * @param position stanowisko pracownika
     * @param employeeId ID pracownika
     */
    public Employee(String name, int age, String position, int employeeId) {
        super(name, age);
        this.employeeId = employeeId;
        this.position = position;
        if (employeeId >= nextId) {
            nextId = employeeId + 1;
        }
    }

    // Gettery i settery
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Statyczna metoda zwracająca liczbę utworzonych pracowników
     * @return liczba pracowników
     */
    public static int getTotalEmployees() {
        return nextId - 1000;
    }

    @Override
    public String getInfo() {
        return "Pracownik: " + name + " (ID: " + employeeId + "), stanowisko: " + position + ", wiek: " + age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId, position);
    }
}
