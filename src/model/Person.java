package model;

import java.util.Objects;

/**
 * Abstrakcyjna klasa bazowa reprezentująca osobę w systemie.
 * Stanowi podstawę hierarchii dziedziczenia dla klas Customer i Employee.
 * Demonstruje zastosowanie klasy abstrakcyjnej z metodą abstrakcyjną getInfo().
 */

public abstract class Person {
    protected String name;
    protected int age;

    /**
     * Konstruktor klasy Person
     * @param name imię osoby
     * @param age wiek osoby
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Gettery i settery (hermetyzacja)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
