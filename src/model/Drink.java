package model;

import java.util.Objects;

/**
 * Klasa reprezentująca napój w menu kawiarni.
 * Dziedziczy po abstrakcyjnej klasie Product i implementuje metodę getDescription().
 * Rozszerza funkcjonalność o temperaturę i objętość napoju.
 */

public class Drink extends Product {
    private boolean isHot;
    private int volumeInMl;

    /**
     * Konstruktor klasy Drink
     * @param name nazwa napoju
     * @param price cena napoju
     * @param isHot czy napój jest gorący
     * @param volumeInMl objętość napoju w mililitrach
     */
    public Drink(String name, double price, boolean isHot, int volumeInMl) {
        super(name, price);
        this.isHot = isHot;
        this.volumeInMl = volumeInMl;
    }

    // Gettery i settery
    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public int getVolumeInMl() {
        return volumeInMl;
    }

    public void setVolumeInMl(int volumeInMl) {
        this.volumeInMl = volumeInMl;
    }

    @Override
    public String getDescription() {
        String temperature = isHot ? "gorący" : "zimny";
        return name + " (" + temperature + ", " + volumeInMl + "ml) - " + String.format("%.2f", price) + " zł";
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isHot=" + isHot +
                ", volumeInMl=" + volumeInMl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return isHot == drink.isHot && volumeInMl == drink.volumeInMl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isHot, volumeInMl);
    }
}
