package ru.vsu.cs.course2.oop;

import ru.vsu.cs.course2.oop.delivery.Delivery;
import ru.vsu.cs.course2.oop.delivery.DeliveryException;
import ru.vsu.cs.course2.oop.order.Order;
import ru.vsu.cs.course2.oop.order.OrderException;
import ru.vsu.cs.course2.oop.product.Food;
import ru.vsu.cs.course2.oop.product.Item;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Объекты еды и предметов
        System.out.println("----- Объекты еды и предметов -----");
        Food pr = new Food("Watermelon", 12.2f, "Хорошие арбузы отвечаю", 10, LocalDate.of(2024, 11, 10));
        Food pr1 = new Food("Apple", 12.2f, "Хорошие яблоки отвечаю", 10, LocalDate.of(2024, 9, 10));
        System.out.println(pr);
        System.out.println(pr1);
        Item it = new Item("Laptop", 100_000, "UltraOmegaGamingLaptop3000", 1, 13);
        Item it2 = new Item("Laptop2", 100_000, "UltraOmegaGamingLaptop4000", 3);
        System.out.println(it2.getUnitPriceWithDiscount(0.1f));
        System.out.println(it2.getTotalProductCost());
        System.out.println(it);
        System.out.println(it2);

        System.out.println();
        // Заказы еды и предметов выше
        System.out.println("----- Заказы еды и предметов выше -----");
        Order orderFood = new Order(pr, 5, "Stariy Oskol");
        Order orderItem = new Order(it, 1, "Stariy Oskol");
        System.out.println(orderFood);
        System.out.println(orderItem);

        System.out.println();
        // Подразумевающаяся работа с классом Delivery
        System.out.println("----- Подразумевающаяся работа с классом Delivery -----");
        System.out.println(orderFood.startDelivery(LocalDate.of(2024, 11, 10).atStartOfDay()));
        System.out.println(orderItem.startDelivery(LocalDate.of(2024, 11, 10).atStartOfDay()));

        System.out.println();
        // Ручное создание Delivery (По сути то же самое, потому что выше просто вызывается создание экземпляра из самого класса Order)
        System.out.println("----- Ручное создание Delivery -----");
        Order orderFood1 = new Order(pr, 5, "Stariy Oskol");
        Delivery foodDelivery1 = null;
        try {
            foodDelivery1 = new Delivery(orderFood1, LocalDate.of(2024, 11, 10).atStartOfDay());
        } catch (DeliveryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(foodDelivery1);

        System.out.println();
        // Попытка заказать ноутбук, когда мы уже его заказали(он был один, его больше нет)
        System.out.println("----- Попытка заказать то, чего больше нет -----");
        Order orderItem1 = new Order(it, 1, "Stariy Oskol"); // не создается даже в качестве заказа
        System.out.println(orderItem1.startDelivery(LocalDate.of(2024, 11, 10).atStartOfDay()));

        // Тест exception'а
        Item it3 = new Item("Laptop3", 700_007, "UltraOmegaGamingLaptop7Z7Z", 3);
        Order orderIt3 = new Order(it3, -2, "HEHEHAW");
        try {
            orderIt3.validateOrder();
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }
    }
}
