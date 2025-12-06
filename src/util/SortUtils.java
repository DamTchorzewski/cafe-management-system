package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Klasa narzędziowa zawierająca generyczne metody sortowania.
 * Demonstruje zastosowanie typów generycznych z ograniczeniami (bounded types).
 * Obsługuje sortowanie zarówno dla obiektów implementujących Comparable,
 * jak i z wykorzystaniem zewnętrznych obiektów Comparator.
 */


public class SortUtils {
    
    /**
     * Sortuje listę obiektów używając naturalnego porządku (Comparable)
     * @param <T> typ obiektów implementujących Comparable
     * @param list lista do posortowania
     */
    public static <T extends Comparable<T>> void sortList(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list);
    }
    
    /**
     * Sortuje listę obiektów używając dostarczonego Comparatora
     * @param <T> typ obiektów do posortowania
     * @param list lista do posortowania
     * @param comparator komparator definiujący porządek sortowania
     */
    public static <T> void sortList(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty() || comparator == null) {
            return;
        }
        Collections.sort(list, comparator);
    }
    
    /**
     * Sortuje listę w odwrotnej kolejności naturalnego porządku
     * @param <T> typ obiektów implementujących Comparable
     * @param list lista do posortowania
     */
    public static <T extends Comparable<T>> void sortListReversed(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, Collections.reverseOrder());
    }
}
