package ru.vsu.cs.course2.oop;

import ru.vsu.cs.course2.oop.delivery.Delivery;
import ru.vsu.cs.course2.oop.delivery.DeliveryException;
import ru.vsu.cs.course2.oop.order.Order;
import ru.vsu.cs.course2.oop.order.OrderException;
import ru.vsu.cs.course2.oop.product.Food;
import ru.vsu.cs.course2.oop.product.Item;
import ru.vsu.cs.course2.oop.product.Product;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

        System.out.println();
        // Тест exception'а
        System.out.println("----- Тест exception -----");
        Item it3 = new Item("Laptop3", 700_007, "UltraOmegaGamingLaptop7Z7Z", 3);
        Order orderIt3 = new Order(it3, -2, "HEHEHAW");
        try {
            orderIt3.validateOrder();
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        //Тест duration и localdatetime + period + duration
        System.out.println("----- Тест LocalDateTime -----");
        Item it4 = new Item("Laptop4", 4444, "UltraOmegaGamingLaptop4444", 4);
        Order orderIt4 = new Order(it4, 2, "Toilet");
        Delivery orderIt4Delivery = orderIt4.startDelivery("21-12-2025 18:00");
        System.out.println("Months of warranty " + it4.getMonthsOfWarranty().getMonths());
        System.out.println(orderIt4Delivery);
        System.out.println(orderIt4Delivery.getDeliveryTimeDeadline().format(Delivery.DATE_FORMATTER));
        System.out.println(orderIt4Delivery.timeUntilDeadline().toDaysPart() + ":"+ orderIt4Delivery.timeUntilDeadline().toHoursPart() + ":" + orderIt4Delivery.timeUntilDeadline().toMinutesPart());
        List.of(it, it3);
        System.out.println();
        //
        System.out.println("----- /// -----");
        Item bowlingLight = new Item("Bowling light", 100, "Bowling light", 100, 0);
        Item googlePixel8pro = new Item("Google pixel 8 pro", 100_000, "The best phone to ever exist", 11, 24);
        Item googlePixel8a = new Item("Google pixel 8a", 50_000, "Latest releasse", 7, 24);
        Item iphone16ProMax = new Item("iPhone 16 pro max", 88_888, "Incredible camera experience", 77, 12);
        Item iphone16 = new Item("iPhone 16", 77_777, "New camera button!", 88, 12);
        Item hermesSaddle = new Item("Hermes saddle", 111_111, "Dubai showing off", 3, 60);
        Item smartSpeaker = new Item("Alice mini 2", 10_000, "Yandex prod.", 123, 12);

        Food watermelon = new Food("Watermelon", 50.2f, "Хорошие арбузы отвечаю", 122, LocalDate.of(2024, 12, 31));
        Food apple = new Food("Apple", 20.0f, "Хорошие яблоки отвечаю", 166, LocalDate.of(2024, 12, 31));
        Food orange = new Food("Orange", 37.5f, "Хорошие апельсины отвечаю", 155, LocalDate.of(2024, 12, 31));

        List<Product> products = new ArrayList<>(List.of(bowlingLight, googlePixel8pro, googlePixel8a, iphone16ProMax, iphone16, hermesSaddle, smartSpeaker, watermelon, apple, orange));

        System.out.println(products);

        Order orderPixel8Pro = new Order(googlePixel8pro, 1, "Stariy Oskol, Solnechniy, 3");
        Order orderPixel8ProFourMore = new Order(googlePixel8pro, 4, "Stariy Oskol, Solnechniy, 3");
        Order orderPixel8a = new Order(googlePixel8a, 1, "Voronezh, Plekhanovskaya, 6");
        Order orderBowlingLights = new Order(bowlingLight, 3, "Voronezh, Lomonosova 12");
        Order orderIPhone16promax = new Order(iphone16ProMax, 1, "Moscow, Lenina 33");
        Order orderIPhone16 = new Order(iphone16, 1, "Domodedovo, Severniy 166");
        Order orderIPhone16TwoMore = new Order(iphone16, 2, "Domodedovo, Severniy 166");
        Order orderHermesSaddle = new Order(hermesSaddle, 2, "Dubai, tower");
        Order orderSmartSpeaker = new Order(smartSpeaker, 2, "Voronezh, Universitetskaya, 1");
        Order orderSmartSpeakerAgain = new Order(smartSpeaker, 4, "Voronezh, Universitetskaya, 1");
        Order orderSmartSpeakerAgainAndAgain = new Order(smartSpeaker, 3, "Voronezh, Universitetskaya, 1");

        Order orderApple = new Order(apple, 15, "Stariy Oskol, Solnechniy, 3");
        Order orderWatermelons = new Order(watermelon, 5, "Stariy Oskol, Solnechniy, 3");
        Order orderOranges = new Order(orange, 11, "Voronezh, Plekhanovskaya, 6");
        Order orderOrangesAgain = new Order(orange, 25, "Voronezh, Plekhanovskaya, 6");

        List<Order> orderList = new ArrayList<>(List.of(orderPixel8Pro, orderPixel8a, orderBowlingLights, orderIPhone16promax, orderIPhone16, orderHermesSaddle, orderApple, orderSmartSpeaker, orderWatermelons, orderOranges, orderSmartSpeakerAgain, orderSmartSpeakerAgainAndAgain, orderOrangesAgain, orderIPhone16TwoMore, orderPixel8ProFourMore));


        Map<Product, Double> productFloatMap = orderList.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.averagingDouble(Order::getTotalPrice)
                ));
        System.out.println(productFloatMap);
        System.out.println(productFloatMap.get(googlePixel8pro));
        System.out.println(productFloatMap.get(smartSpeaker));

//        System.out.println(orderList.size());






















//        Delivery deliveryPixel8Pro = orderPixel8Pro.startDelivery("31-12-2025 18:00");
//        Delivery deliveryPixel8a = orderPixel8a.startDelivery("31-12-2025 18:00");
//        Delivery deliveryBowlingLights = orderBowlingLights.startDelivery("31-12-2025 18:00");
//        Delivery deliveryIPhone16promax = orderIPhone16promax.startDelivery("31-12-2025 18:00");
//        Delivery deliveryIphone16 = orderIPhone16.startDelivery("31-12-2025 18:00");
//        Delivery deliveryHermesSaddle = orderHermesSaddle.startDelivery("31-12-2025 18:00");
//        Delivery deliveryApple = orderApple.startDelivery("31-12-2025 18:00");
//
//        List<Delivery> deliveryList = new ArrayList<>(List.of(deliveryPixel8Pro, deliveryPixel8a, deliveryBowlingLights, deliveryIPhone16promax, deliveryIphone16, deliveryHermesSaddle, deliveryApple));
//
//        System.out.println(deliveryList);
    }
}
