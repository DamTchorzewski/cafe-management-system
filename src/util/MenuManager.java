package util;

import model.Drink;
import model.Food;
import model.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Klasa narzędziowa do zarządzania menu kawiarni.
 * Zapewnia funkcjonalność filtrowania, sortowania i wyszukiwania produktów.
 * Wykorzystuje interfejs Comparator (z anonimowymi klasami) do sortowania produktów
 * według różnych kryteriów (nazwa, cena).
 */
public class MenuManager {
    private List<Product> products;
    
    /**
     * Konstruktor bezparametryczny
     */
    public MenuManager() {
        this.products = new ArrayList<>();
    }
    
    /**
     * Konstruktor z listą produktów
     * @param products lista produktów
     */
    public MenuManager(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
    
    /**
     * Dodaje produkt do menu
     * @param product produkt do dodania
     */
    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }
    
    /**
     * Usuwa produkt z menu
     * @param product produkt do usunięcia
     * @return true jeśli produkt został usunięty
     */
    public boolean removeProduct(Product product) {
        return products.remove(product);
    }
    
    /**
     * Zwraca wszystkie produkty
     * @return lista produktów
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    /**
     * Wyświetla pełne menu
     */
    public void showMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          MENU KAWIARNI                   ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        showDrinks();
        showFood();
    }
    
    /**
     * Wyświetla tylko napoje
     */
    public void showDrinks() {
        System.out.println("\nNAPOJE:");
        System.out.println("────────────────────────────────────────────");
        for (Product p : products) {
            if (p instanceof Drink) {
                System.out.println("  • " + p.getDescription());
            }
        }
    }
    
    /**
     * Wyświetla tylko jedzenie
     */
    public void showFood() {
        System.out.println("\nJEDZENIE:");
        System.out.println("────────────────────────────────────────────");
        for (Product p : products) {
            if (p instanceof Food) {
                System.out.println("  • " + p.getDescription());
            }
        }
    }
    
    /**
     * Zwraca listę produktów posortowaną według nazwy
     * Demonstracja użycia Comparator
     * @return posortowana lista produktów
     */
    public List<Product> getProductsSortedByName() {
        List<Product> sorted = new ArrayList<>(products);
        // Comparator jako anonimowa klasa (bez method reference)
        Comparator<Product> nameComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
        SortUtils.sortList(sorted, nameComparator);
        return sorted;
    }
    
    /**
     * Zwraca listę produktów posortowaną według ceny
     * Demonstracja użycia Comparator
     * @return posortowana lista produktów
     */
    public List<Product> getProductsSortedByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        // Comparator jako anonimowa klasa (bez method reference)
        Comparator<Product> priceComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        };
        SortUtils.sortList(sorted, priceComparator);
        return sorted;
    }
    
    /**
     * Filtruje produkty w określonym przedziale cenowym
     * @param minPrice minimalna cena
     * @param maxPrice maksymalna cena
     * @return lista produktów w danym przedziale cenowym
     */
    public List<Product> filterByPriceRange(double minPrice, double maxPrice) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                filtered.add(p);
            }
        }
        return filtered;
    }
    
    /**
     * Zwraca liczbę produktów w menu
     * @return liczba produktów
     */
    public int getProductCount() {
        return products.size();
    }
    
    /**
     * Wyszukuje produkt po nazwie
     * @param name nazwa produktu
     * @return produkt lub null jeśli nie znaleziono
     */
    public Product findProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}
