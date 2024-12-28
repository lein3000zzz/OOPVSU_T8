package ru.vsu.cs.course2.oop.product;

import java.time.Duration;
import java.time.Period;

public class Item extends Product implements iAdditionalProductInfo {
    private Period monthsOfWarranty = Period.ofMonths(6);
    private static float taxRate = 0.1f;

    @Override
    public String toString() {
        return "Item{" +
                "monthsOfWarranty=" + monthsOfWarranty +
                "} " + super.toString();
    }

    @Override
    public float getUnitPrice() {
        return monthsOfWarranty.getMonths() > 12 ? super.getPrice() * (1 + taxRate) : super.getPrice();
    }

    @Override
    public float getTotalProductCost() {
        return getUnitPrice() * super.getQuantity();
    }

    public Item(String name, int price, String description, int quantity, int monthsOfWarranty) {
        super(name, price, description, quantity);
        this.monthsOfWarranty = Period.ofMonths(monthsOfWarranty);
    }

    public Item(String name, int price, String description, int quantity) {
        super(name, price, description, quantity);
    }

    public Item(String name, int price, String description) {
        super(name, price, description);
    }

    public static float getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(float taxRate) {
        Item.taxRate = taxRate;
    }
    public Period getMonthsOfWarranty() {
        return monthsOfWarranty;
    }

    public void setMonthsOfWarranty(Period monthsOfWarranty) {
        this.monthsOfWarranty = monthsOfWarranty;
    }
}
