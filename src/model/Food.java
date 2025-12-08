package model;

import java.util.Objects;

/**
 * Klasa reprezentująca jedzenie w menu kawiarni.
 * Dziedziczy po abstrakcyjnej klasie Product i implementuje metodę getDescription().
 * Rozszerza funkcjonalność o wagę i informację czy jest wegetariańskie.
 */

public class Food extends Product {
    private boolean isVegetarian;
    private int weightInGrams;

    /**
     * Konstruktor klasy Food
     * @param name nazwa potrawy
     * @param price cena potrawy
     * @param isVegetarian czy jest wegetariańska
     * @param weightInGrams waga w gramach
     */
    public Food(String name, double price, boolean isVegetarian, int weightInGrams) {
        super(name, price);
        this.isVegetarian = isVegetarian;
        this.weightInGrams = weightInGrams;
    }

    // Gettery i settery
    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    @Override
    public String getDescription() {
        String vegInfo = isVegetarian ? "[WEGE]" : "";

        return name + " " + vegInfo + " (" + weightInGrams + "g) - " + price + " zł";

    }

    @Override
    public String toString() {
        return "Food{" + "name=" + name + ", price=" + price + ", isVegetarian=" + isVegetarian + ", weightInGrams=" + weightInGrams + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return isVegetarian == food.isVegetarian && weightInGrams == food.weightInGrams;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isVegetarian, weightInGrams);
    }
}
