package ru.vsu.cs.course2.oop.product;

import java.time.LocalDate;

public class Food extends Product implements iAdditionalProductInfo {
    private LocalDate expirationDate;

    {
        expirationDate = LocalDate.now();
        System.out.println("Expiration_date hasn`t been added, adding now: " + expirationDate);
    }

    @Override
    public String toString() {
        return "Food{" +
                "expirationDate=" + expirationDate +
                "} " + super.toString();
    }

    public Food(String name, float price, String description, int quantity, LocalDate expirationDate) {
        super(name, price, description, quantity);
        this.expirationDate = expirationDate;
    }

    public Food(String name, float price, String description, int quantity) {
        super(name, price, description, quantity);
    }
    @Override
    public float getUnitPrice() {
        return expirationDate.isAfter(LocalDate.now()) ? super.getPrice() : super.getPrice() / 2;
    }

    @Override
    public float getTotalProductCost() {
        return getUnitPrice() * super.getQuantity();
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
