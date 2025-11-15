/**
 * Лабораторна робота №6.
 * <p>
 * Тема: Робота з колекціями в мові програмування Java. <br>
 * Виконав: Співак Артем Михайлович. <br>
 * Група: ІО-35. <br>
 * Варіант: 3519. <br>
 * Завдання для ЛР5: <br>
 * Визначити ієрархію легкових автомобілів. Створити таксопарк.
 * Порахувати вартість автопарку. Провести сортування автомобілів парку за витратами палива.
 * Знайти автомобіль у компанії, що відповідає заданому діапазону швидкості автомобіля. (C13 = 9). <br>
 * Завдання для ЛР6: <br>
 * Інтерфейс, який реалізує колекція: {@code Set} (C2 = 1). <br>
 * Внутрішня структура колекції: Масив із початковою кількістю елементів 15 та збільшенням кількості
 * елементів на 30% (C3 = 0). <br>
 * <p>
 * Клас {@code Main} запускає виконання Лабораторної роботи 5. <br>
 * Клас {@code MainSetDemo} запускає виконання Лабораторної роботи 6,
 * показуючи роботу власної реалізації множини {@link ArrayTaxiSet}. <br>
 */

package taxipark;

/**
 * Точка входу в програму.
 * Демонструє роботу з ієрархією легкових автомобілів:
 * створення таксопарку, підрахунок вартості, сортування за витратою пального
 * та пошук автомобілів у заданому діапазоні швидкості.
 */
public class Main {
    /**
     * Головний метод програми.
     */
    public static void main(String[] args) {
        try {
            Car[] carPark = {
                    new EconomyCar("Toyota", "Yaris", 2018, 175, 5.5, 11500, false),
                    new PremiumCar("Audi", "A8 L 55 TFSI", 2020, 250, 8.0, 80000, true),
                    new EconomyCar("Skoda", "Fabia", 2017, 172, 5.0, 10800, false),
                    new EconomyCar("Hyundai", "i20", 2019, 182, 5.7, 12000, false),
                    new BusinessCar("Toyota", "Camry", 2020, 210, 7.2, 28000, false),
                    new BusinessCar("Mazda", "6", 2021, 215, 6.8, 25800, false),
                    new SuperCar("Porsche", "911 Carrera", 2020, 293, 9.0, 110000, true, 4.2),
                    new PremiumCar("BMW", "740i", 2020, 250, 8.2, 82000, true),
                    new BusinessCar("Volkswagen", "Passat B8", 2019, 220, 6.0, 24500, true),
                    new PremiumCar("Mercedes-Benz", "S450", 2019, 250, 8.5, 78000, true)
            };
            TaxiPark park = new TaxiPark(carPark);
            System.out.println("---------------------------------   Початковий склад автопарку" +
                    "   --------------------------------");
            park.printTable();
            System.out.println("\n---------------------------   Загальна вартість автопарку: " + park.getTotalPrice() +
                    "$   --------------------------");
            park.sortByFuelConsumption();
            System.out.println("\n-----------------------------   Відсортовано за витратами пального" +
                    "   ----------------------------");
            park.printTable();
            System.out.println("\n------------------------   Авто зі швидкістю у діапазоні 215–230 км/год" +
                    "   -----------------------");
            Car[] found = park.findBySpeedRange(215, 230);
            if (found.length == 0) {
                System.out.println("Таких авто немає...");
            } else {
                TaxiPark foundPark = new TaxiPark(found);
                foundPark.printTable();
            }
        } catch (Exception ex) {
            System.err.println("Помилка: " + ex.getMessage());
        }
    }
}
