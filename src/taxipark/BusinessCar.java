package taxipark;

/**
 * Автомобіль бізнес-класу.
 * Зазвичай має вищий рівень комфорту та обладнання.
 */
public class BusinessCar extends Car {
    /**
     * Створює екземпляр авто бізнес-класу.
     */
    public BusinessCar(String brand, String model, int year, int maxSpeed, double fuelConsumption, double price,
                       boolean leatherInterior) {
        super(brand, model, year, maxSpeed, fuelConsumption, price, leatherInterior);
    }
}
