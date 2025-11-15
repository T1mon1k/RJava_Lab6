package taxipark;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас, що описує таксопарк.
 * Містить масив легкових автомобілів та надає операції над ними:
 * підрахунок загальної вартості, сортування за витратою пального,
 * пошук за діапазоном швидкості та табличний вивід.
 */
public class TaxiPark {
    /** Масив автомобілів таксопарку. */
    private final Car[] cars;

    /**
     * Створює новий таксопарк.
     */
    public TaxiPark(Car[] cars) {
        if (cars == null)
            throw new IllegalArgumentException("Масив машин не може бути null");
        if (cars.length == 0)
            throw new IllegalArgumentException("Таксопарк не може бути порожнім");
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                throw new IllegalArgumentException("Елемент масиву " + i + " дорівнює null");
            }
        }
        this.cars = cars;
    }

    /**
     * Повертає сумарну вартість усіх автомобілів в таксопарку.
     */
    public double getTotalPrice() {
        double sum = 0;
        for (Car c : cars) sum += c.getPrice();
        return sum;
    }

    /**
     * Сортує автомобілі в таксопарку за витратою пального
     * у порядку зростання (від найекономніших до найвитратніших).
     */
    public void sortByFuelConsumption() {
        Arrays.sort(cars, Comparator.comparingDouble(Car::getFuelConsumption));
    }

    /**
     * Повертає масив автомобілів, максимальна швидкість яких
     * знаходиться у вказаному діапазоні.
     */
    public Car[] findBySpeedRange(int minSpeed, int maxSpeed) {
        if (minSpeed <= 0 || maxSpeed <= 0)
            throw new IllegalArgumentException("Швидкість має бути більше 0!");
        if (minSpeed > maxSpeed)
            throw new IllegalArgumentException("Не вірно вказаний діапазон швидкості!");
        return Arrays.stream(cars)
                .filter(c -> c.getMaxSpeed() >= minSpeed && c.getMaxSpeed() <= maxSpeed).toArray(Car[]::new);
    }

    /**
     * Повертає масив автомобілів таксопарку.
     * Метод надає лише читання, масив в полі є final.
     */
    public Car[] getCars() {
        return cars;
    }

    /**
     * Виводить вміст таксопарку у вигляді вирівняної таблиці
     * в стандартний потік виводу (консоль).
     */
    public void printTable() {
        System.out.printf("%-10s %-15s %-13s %-6s %-13s %-13s %-8s %-12s%n",
                "Тип", "Марка", "Модель", "Рік", "Макс. швидк.", "Витр. пальн.", "Ціна", "Шкір. салон");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Car c : cars) {
            System.out.printf("%-10s %-15s %-13s %-10d %-13d %-9.1f %-12.0f %-12s%n",
                    getCarType(c),
                    c.getBrand(),
                    c.getModel(),
                    c.getYear(),
                    c.getMaxSpeed(),
                    c.getFuelConsumption(),
                    c.getPrice(),
                    c.hasLeatherInterior() ? "так" : "ні"
            );
        }
    }

    /**
     * Допоміжний метод для отримання типу автомобіля у вигляді рядка.
     */
    private String getCarType(Car c) {
        return switch (c.getClass().getSimpleName()) {
            case "EconomyCar" -> "Економ";
            case "BusinessCar" -> "Бізнес";
            case "PremiumCar" -> "Преміум";
            case "SuperCar" -> "Суперкар";
            default -> "Авто";
        };
    }
}
