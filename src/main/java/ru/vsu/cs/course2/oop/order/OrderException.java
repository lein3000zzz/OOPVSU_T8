package ru.vsu.cs.course2.oop.order;

public class OrderException extends Exception {
    public OrderException() {
        super("An error occurred with the order.");
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }
}
