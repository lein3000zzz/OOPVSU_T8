package ru.vsu.cs.course2.oop.delivery;

import ru.vsu.cs.course2.oop.order.Order;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Delivery {
    private Order order;
    private LocalDateTime createdAt;
    private LocalDateTime deliveryTimeDeadline;
    private static String deliveryService;
    long trackingNumber;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return trackingNumber == delivery.trackingNumber && Objects.equals(order, delivery.order) && Objects.equals(createdAt, delivery.createdAt) && Objects.equals(deliveryTimeDeadline, delivery.deliveryTimeDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, createdAt, deliveryTimeDeadline, trackingNumber);
    }

    static {
        deliveryService = String.valueOf(DeliveryServices.values()[LocalDateTime.MIN.getDayOfMonth() % 5]);
        System.out.println("Today`s delivery company: " + deliveryService);
    }

    @Override
    public String toString() {
        return (!order.getStatus().equals("Cancelled")) ? "Delivery{" +
                "product=" + order.getProduct().getName() +
                ", createdAt=" + createdAt +
                ", deliveryTimeDeadline= " + deliveryTimeDeadline +
                ", trackingNumber=\"" + trackingNumber + "\"" +
                "}" : "Order cancelled";
    }

    public Delivery(Order order, LocalDateTime deliveryTimeDeadline) throws DeliveryException {
        this.order = order;
        this.deliveryTimeDeadline = deliveryTimeDeadline;
        this.createdAt = LocalDateTime.now();
//        if (!this.createdAt.isBefore(deliveryTimeDeadline) || order.getStatus().equals("Cancelled") || order.getStatus().equals("In progress")) {
//            System.out.println("There was a problem processing ur order");
//            order.setStatus("Cancelled");
//            return;
//        }
        if (!this.createdAt.isBefore(deliveryTimeDeadline) || order.getStatus().equals("Cancelled") || order.getStatus().equals("In progress")) {
            order.setStatus("Cancelled");
            throw new DeliveryException("There was a problem processing your order. Order status: " + order.getStatus());
        }
        this.trackingNumber = System.currentTimeMillis();
        System.out.printf("Your TN: %d. Delivery in progress\n", trackingNumber);
        order.setStatus("In progress");
    }

    public Delivery(Order order, String deliveryTimeDeadline) throws DeliveryException {
        this.order = order;
        this.deliveryTimeDeadline = LocalDateTime.parse(deliveryTimeDeadline, DATE_FORMATTER);
        this.createdAt = LocalDateTime.now();
//        if (!this.createdAt.isBefore(deliveryTimeDeadline) || order.getStatus().equals("Cancelled") || order.getStatus().equals("In progress")) {
//            System.out.println("There was a problem processing ur order");
//            order.setStatus("Cancelled");
//            return;
//        }
        if (!this.createdAt.isBefore(this.deliveryTimeDeadline) || order.getStatus().equals("Cancelled") || order.getStatus().equals("In progress")) {
            order.setStatus("Cancelled");
            throw new DeliveryException("There was a problem processing your order. Order status: " + order.getStatus());
        }
        this.trackingNumber = System.currentTimeMillis();
        System.out.printf("Your TN: %d. Delivery in progress\n", trackingNumber);
        order.setStatus("In progress");
    }

    public Duration timeUntilDeadline() {
        if (deliveryTimeDeadline.isBefore(createdAt)) {
            System.out.println("Deadline cannot be before the order date.");
        }
        return Duration.between(LocalDateTime.now(), deliveryTimeDeadline);
    }

    public static String getDeliveryService() {
        return deliveryService;
    }

    public static void setDeliveryService(String deliveryService) {
        Delivery.deliveryService = deliveryService;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) throws DeliveryException {
        if (createdAt == null || createdAt.isAfter(LocalDateTime.now())) {
            throw new DeliveryException("Invalid creation date");
        }
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeliveryTimeDeadline() {
        return deliveryTimeDeadline;
    }

    public void setDeliveryTimeDeadline(LocalDateTime deliveryTimeDeadline) {
        this.deliveryTimeDeadline = deliveryTimeDeadline;
    }


}
