package taxipark;

/**
 * Абстрактний базовий клас, що описує легковий автомобіль таксопарку.
 * Містить спільні для всіх авто характеристики:
 * марка, модель, рік випуску, максимальна швидкість, витрата пального,
 * ціна та наявність шкіряного салону.
 */
public abstract class Car {
    private final String brand;
    private final String model;
    private final int year;
    private final int maxSpeed;
    private final double fuelConsumption;
    private final double price;
    private final boolean leatherInterior;

    /**
     * Створює новий екземпляр автомобіля.
     */
    public Car(String brand, String model, int year, int maxSpeed, double fuelConsumption, double price,
               boolean leatherInterior) {
        if (brand == null || brand.isBlank())
            throw new IllegalArgumentException("Марка не може бути порожньою!");
        if (model == null || model.isBlank())
            throw new IllegalArgumentException("Модель не може бути порожньою!");
        if (year < 1950 || year > 2025)
            throw new IllegalArgumentException("Некоректний рік випуску!");
        if (maxSpeed <= 0)
            throw new IllegalArgumentException("Макс. швидкість має бути більше 0!");
        if (fuelConsumption <= 0)
            throw new IllegalArgumentException("Витрата пального має бути більше 0!");
        if (price <= 0)
            throw new IllegalArgumentException("Ціна має бути більше 0!");

        this.brand = brand;
        this.model = model;
        this.year = year;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
        this.price = price;
        this.leatherInterior = leatherInterior;
    }

    /**
     * Повертає марку автомобіля.
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Повертає модель автомобіля.
     */
    public String getModel() {
        return model;
    }
    /**
     * Повертає рік випуску автомобіля.
     */
    public int getYear() {
        return year;
    }
    /**
     * Повертає максимальну швидкість автомобіля.
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }
    /**
     * Повертає витрату пального автомобіля.
     */
    public double getFuelConsumption() {
        return fuelConsumption;
    }
    /**
     * Повертає вартість автомобіля.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Повертає, чи має автомобіль шкіряний салон.
     */
    public boolean hasLeatherInterior() {
        return leatherInterior;
    }
}
