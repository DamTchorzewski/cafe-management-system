package model;

import java.util.Objects;

/**
 * Abstrakcyjna klasa bazowa reprezentująca osobę w systemie.
 * Stanowi podstawę hierarchii dziedziczenia dla klas Customer i Employee.
 * Demonstruje zastosowanie klasy abstrakcyjnej z metodą abstrakcyjną getInfo().
 */

public abstract class Person {
    protected String name;

    /**
     * Konstruktor klasy Person
     * @param name imię osoby
     */
    public Person(String name) {
        this.name = name;
    }

    // Gettery i settery (hermetyzacja)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda abstrakcyjna zwracająca informacje o osobie
     * @return String z informacjami o osobie
     */
    public abstract String getInfo();

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
