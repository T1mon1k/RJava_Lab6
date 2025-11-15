package taxipark;

/**
 * Преміальний легковий автомобіль.
 * Характеризується високою вартістю та великою кількістю опцій.
 */
public class PremiumCar extends Car {
    /**
     * Створює екземпляр преміального автомобіля.
     */
    public PremiumCar(String brand, String model, int year, int maxSpeed, double fuelConsumption, double price,
                      boolean leatherInterior) {
        super(brand, model, year, maxSpeed, fuelConsumption, price, leatherInterior);
    }
}
