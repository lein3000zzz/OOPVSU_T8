package ru.vsu.cs.course2.oop.delivery;

public class DeliveryException extends Exception {
    public DeliveryException() {
        super("An error occurred whilst trying to create a new delivery.");
    }
    public DeliveryException(String message) {
        super(message);
    }
    public DeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryException(Throwable cause) {
        super(cause);
    }
}
