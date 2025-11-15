package taxipark;

/**
 * Автомобіль економ-класу.
 * Має мінімальний набір опцій та невелику витрату пального.
 */
public class EconomyCar extends Car {
    /**
     * Створює екземпляр авто економ-класу.
     */
    public EconomyCar(String brand, String model, int year, int maxSpeed, double fuelConsumption, double price,
                      boolean leatherInterior) {
        super(brand, model, year, maxSpeed, fuelConsumption, price, leatherInterior);
    }
}
