package ru.vsu.cs.course2.oop.delivery;

public enum DeliveryServices {
    CDEK(2),
    POST_SERVICE(1),
    YANDEX(1.5f),
    DPD(3),
    BOXBERRY(1.5f);
    private float deliveryFee;

    DeliveryServices(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }
}
