package ru.vsu.cs.course2.oop.order;

import ru.vsu.cs.course2.oop.delivery.Delivery;
import ru.vsu.cs.course2.oop.delivery.DeliveryException;
import ru.vsu.cs.course2.oop.product.Product;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Product product;
    private int quantity;
    private float totalPrice;
    private String status;
    private String shippingAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity && Float.compare(totalPrice, order.totalPrice) == 0 && Objects.equals(product, order.product) && Objects.equals(shippingAddress, order.shippingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, totalPrice, shippingAddress);
    }

    static {
        System.out.println("Первый заказ в системе!");
    }

    {
        quantity = 1;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                ", totalPrice = " + totalPrice +
                ", status=\"" + status + "\"" +
                ", shippingAddress=\"" + shippingAddress + "\"" +
                "}";
    }

    public Order(Product product, int quantity, String shippingAddress) {
        this.product = product;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        if (quantity > product.getQuantity()) {
            System.out.println("Out of stock");
            this.status = "Cancelled";
            return;
        }
        this.status = "created";
        this.totalPrice = product.getPrice() * quantity;
        product.subtractQuantity(quantity);
    }

    public Order(Product product, String shippingAddress) {
        this.product = product;
        this.shippingAddress = shippingAddress;
        if (quantity > product.getQuantity()) {
            System.out.println("Out of stock");
            this.status = "Cancelled";
            return;
        }
        this.status = "created";
        this.totalPrice = product.getPrice() * quantity;
        product.subtractQuantity(quantity);
    }

    public Delivery startDelivery(LocalDateTime deliveryTimeDeadline) {
        try {
            return new Delivery(this, deliveryTimeDeadline);
        } catch (DeliveryException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void validateOrder() throws OrderException {
        if (this.getQuantity() <= 0) {
            throw new OrderException("Order amount must be greater than zero.");
        }
        if (this.getShippingAddress() == null) {
            throw new OrderException("Shipping address is missing.");
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
