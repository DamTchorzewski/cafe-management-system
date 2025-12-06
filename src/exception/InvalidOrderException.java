package exception;

/**
 * Własny wyjątek użytkownika reprezentujący błędy związane z zamówieniami.
 * Dziedziczy po klasie Exception i jest używany do sygnalizowania nieprawidłowych
 * operacji na zamówieniach, takich jak błędne ilości, puste zamówienia czy brak klienta.
 */
public class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }
}
