package taxipark;

/**
 * Суперкар – високопродуктивний спортивний автомобіль
 * із дуже великою максимальною швидкістю та динамікою розгону.
 */
public class SuperCar extends Car {
    /** Час розгону 0–100 км/год, секунди. */
    private final double acceleration;

    /**
     * Створює екземпляр суперкара.
     */
    public SuperCar(String brand, String model, int year, int maxSpeed, double fuelConsumption, double price,
                    boolean leatherInterior, double acceleration) {
        super(brand, model, year, maxSpeed, fuelConsumption, price, leatherInterior);
        if (acceleration <= 0)
            throw new IllegalArgumentException("Розгін має бути більше 0 сек.!");
        this.acceleration = acceleration;
    }

    /**
     * Повертає час розгону 0–100 км/год.
     */
    public double getAcceleration() {
        return acceleration;
    }
}
