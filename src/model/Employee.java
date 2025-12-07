package model;

import java.util.Objects;

/**
 * Klasa reprezentująca pracownika kawiarni.
 * Dziedziczy po abstrakcyjnej klasie Person i implementuje metodę abstrakcyjną getInfo().
 * Rozszerza funkcjonalność o stanowisko i wynagrodzenie.
 */

public class Employee extends Person {
    private int employeeId;
    private String position;
    private double salary;
    private static int nextId = 1000;  // Statyczne pole do automatycznej numeracji pracowników

    /**
     * Konstruktor klasy Employee
     * @param name imię pracownika
     * @param age wiek pracownika
     * @param position stanowisko pracownika
     * @param salary pensja pracownika
     */
    public Employee(String name, int age, String position, double salary) {
        super(name, age);
        this.employeeId = nextId++;
        this.position = position;
        this.salary = salary;
    }
    
    /**
     * Konstruktor bez pensji (domyślna 0)
     * @param name imię pracownika
     * @param age wiek pracownika
     * @param position stanowisko pracownika
     */
    public Employee(String name, int age, String position) {
        this(name, age, position, 0.0);
    }

    /**
     * Konstruktor z określonym ID (do testów)
     * @param name imię pracownika
     * @param age wiek pracownika
     * @param position stanowisko pracownika
     * @param salary pensja pracownika
     * @param employeeId ID pracownika
     */
    public Employee(String name, int age, String position, double salary, int employeeId) {
        super(name, age);
        this.employeeId = employeeId;
        this.position = position;
        this.salary = salary;
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
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
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
        return "Pracownik: " + name + " (ID: " + employeeId + "), stanowisko: " + position + 
               ", pensja: " + formatSalary(salary) + " zł, wiek: " + age;
    }
    
    private String formatSalary(double salary) {
        long cents = Math.round(salary * 100);
        long zloty = cents / 100;
        long grosze = cents % 100;
        return zloty + "." + (grosze < 10 ? "0" : "") + grosze;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && 
               Double.compare(employee.salary, salary) == 0 && 
               Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId, position, salary);
    }
}
