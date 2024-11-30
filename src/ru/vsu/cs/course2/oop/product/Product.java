package ru.vsu.cs.course2.oop.product;

import java.util.Objects;

public class Product {
    private String name;
    private float price;
    private String description;
    private int quantity;
    private static int numberOfProducts;

    static {
        numberOfProducts = 0;
        System.out.println("Первый товар появился в системе");
    }

    {
        numberOfProducts++;
        System.out.println("В системе вот столько товаров: " + numberOfProducts);
        quantity = 1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + name +
                ", price=" + getPrice() +
                ", description=\"" + description + "\"" +
                ", quantity = " + quantity +
                "}";
    }

    public Product(String name, float price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void subtractQuantity(int orderQuantity) {
        if (orderQuantity <= 0) {
            return;
        }
        this.quantity -= orderQuantity;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
}
