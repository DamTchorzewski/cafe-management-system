package util;

/**
 * Klasa narzędziowa zawierająca generyczne metody do operacji na tablicach.
 * Demonstruje zastosowanie typów generycznych i operacji na tablicach.
 */
public class ArrayUtils {
    
    /**
     * Metoda generyczna do wyszukiwania elementu w tablicy
     * @param <T> typ elementów w tablicy
     * @param array tablica do przeszukania
     * @param element element do znalezienia
     * @return indeks elementu lub -1 jeśli nie znaleziono
     */
    public static <T> int findInArray(T[] array, T element) {
        if (array == null || element == null) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Metoda generyczna do zliczania wystąpień elementu w tablicy
     * @param <T> typ elementów w tablicy
     * @param array tablica do przeszukania
     * @param element element do zliczenia
     * @return liczba wystąpień elementu
     */
    public static <T> int countInArray(T[] array, T element) {
        if (array == null || element == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                count++;
            }
        }
        return count;
    }
}
