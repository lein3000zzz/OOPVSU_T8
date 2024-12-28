package ru.vsu.cs.course2.oop.product;

public interface iAdditionalProductInfo {
    DiscountCalculator discountCalculator = (price, discountRate) -> price * (1 - discountRate);
    float getUnitPrice();
    // Идея в том, что у конкретного юзера есть скидка / промик, rate которого мы и передаем в наш метод.
    // То есть цену можно получить, но ставить ее нет смысла, тк мы работаем
    // с этапом уже оформления заказа
    default float getUnitPriceWithDiscount(float discountRate) {
        return discountCalculator.calculateDiscount(getUnitPrice(), discountRate);
    }
    float getTotalProductCost();
    @FunctionalInterface
    interface DiscountCalculator {
        float calculateDiscount(float price, float discountRate);
    }
}
