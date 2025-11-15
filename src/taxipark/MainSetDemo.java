package taxipark;

import java.util.List;

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
public class MainSetDemo {
    /**
     * Точка входу в програму. Демонструє роботу з власною колекцією
     * {@link ArrayTaxiSet}: створення трьох множин, наповнення об'єктами,
     * видалення, очищення та використання методів {@code contains}, {@code containsAll}, {@code isEmpty}.
     */
    public static void main(String[] args) {
        Car c1 = new EconomyCar("Toyota", "Yaris", 2018, 175, 5.5, 11500, false);
        Car c2 = new PremiumCar("Audi", "A8 L 55 TFSI", 2020, 250, 8.0, 80000, true);
        Car c3 = new EconomyCar("Skoda", "Fabia", 2017, 172, 5.0, 10800, false);
        Car c4 = new EconomyCar("Hyundai", "i20", 2019, 182, 5.7, 12000, false);
        Car c5 = new BusinessCar("Toyota", "Camry", 2020, 210, 7.2, 28000, false);
        Car c6 = new BusinessCar("Mazda", "6", 2021, 215, 6.8, 25800, false);
        Car c7 = new SuperCar("Porsche", "911 Carrera", 2020, 293, 9.0, 110000, true, 4.2);
        Car c8 = new PremiumCar("BMW", "740i", 2020, 250, 8.2, 82000, true);
        Car c9 = new BusinessCar("Volkswagen", "Passat B8", 2019, 220, 6.0, 24500, true);
        Car c10 = new PremiumCar("Mercedes-Benz", "S450", 2019, 250, 8.5, 78000, true);
        Car c11 = new EconomyCar("Renault", "Clio", 2018, 175, 5.3, 10500, false);
        Car c12 = new EconomyCar("Ford", "Fiesta", 2019, 180, 5.6, 11200, false);
        Car c13 = new BusinessCar("Honda", "Accord", 2020, 215, 7.0, 27000, false);
        Car c14 = new BusinessCar("Kia", "Optima", 2020, 220, 6.9, 26000, true);
        Car c15 = new PremiumCar("Lexus", "LS500", 2021, 250, 8.6, 89000, true);
        Car c16 = new PremiumCar("Volvo", "S90", 2020, 240, 7.8, 76000, true);
        Car c17 = new SuperCar("Ferrari", "488 GTB", 2019, 330, 12.5, 250000, true, 3.0);
        Car c18 = new SuperCar("Lamborghini", "Huracán Evo", 2020, 325, 13.0, 280000, true, 2.9);
        Car c19 = new BusinessCar("Nissan", "Teana", 2018, 210, 7.4, 23000, false);
        Car c20 = new EconomyCar("Volkswagen", "Polo", 2020, 178, 5.4, 11800, false);

        // 1) Множина set1 — створена порожнім конструктором і заповнена вручну
        ArrayTaxiSet<Car> set1 = new ArrayTaxiSet<>();
        set1.add(c1); set1.add(c2); set1.add(c3); set1.add(c4); set1.add(c5); set1.add(c6); set1.add(c7); set1.add(c8);
        set1.add(c9); set1.add(c10); set1.add(c11); set1.add(c12); set1.add(c13); set1.add(c14); set1.add(c15);
        set1.add(c16); set1.add(c17); // додаємо більше 15 елементів, щоб продемонструвати збільшення масиву

        // 2) Множина set2 — створена конструктором з одним елементом
        ArrayTaxiSet<Car> set2 = new ArrayTaxiSet<>(c4);
        set2.add(c5); set2.add(c1); set2.add(c4); set2.add(c18); set2.add(c19); set2.add(c20); // є дубль, але не буде доданий

        // 3) Множина set3 — створена зі стандартної колекції (List.of)
        ArrayTaxiSet<Car> set3 = new ArrayTaxiSet<>(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c5));
        set3.add(c20); set3.add(c19); // теж є дубль, який не додасться

        System.out.println("---------------------------------------   Set 1: Базовий" +
                "   --------------------------------------");
        printSet(set1);

        System.out.println("\n-------------------------------------   Set 1: Видаляємо c3" +
                "   -----------------------------------");
        set1.remove(c3);
        printSet(set1);

        System.out.println("\n-------------------------------------   Set 1: Видаляємо все" +
                "   ----------------------------------");
        set1.clear();
        printSet(set1);

        System.out.println("\n----------------------------------   Set 2: З одним елементом" +
                "   ---------------------------------");
        printSet(set2);

        System.out.println("\n---------------------   Set 3: створений з стандартної колекції (List.of)" +
                "   ---------------------");
        printSet(set3);

        System.out.println("\n--------------------------------   Set 1 містить c3? " + set1.contains(c3) +
                "   ------------------------------------");
        System.out.println("---------------------------------   Set 1 порожній? " + set1.isEmpty() +
                "   -------------------------------------");
        System.out.println("--------------------------------   Set 2 містить c1? " + set2.contains(c1) +
                "   ------------------------------------");
        System.out.println("------------------------   Set 3 містить всі елементи Set 2? " + set3.containsAll(set2) +
                "   ----------------------------");
    }

    /**
     * Виводить вміст множини {@link ArrayTaxiSet} у вигляді вирівняної таблиці.
     */
    private static void printSet(ArrayTaxiSet<Car> set) {
        System.out.println("-------------------------------------   Розмір множини: " + set.size() +
                "   ------------------------------------");
        System.out.printf("%-10s %-15s %-13s %-6s %-13s %-13s %-8s %-12s%n",
                "Тип", "Марка", "Модель", "Рік", "Макс. швидк.", "Витр. пальн.", "Ціна", "Шкір. салон");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Car c : set) {
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
     * Повертає назву типу автомобіля для відображення в таблиці.
     */
    private static String getCarType(Car c) {
        return switch (c.getClass().getSimpleName()) {
            case "EconomyCar" -> "Економ";
            case "BusinessCar" -> "Бізнес";
            case "PremiumCar" -> "Преміум";
            case "SuperCar" -> "Суперкар";
            default -> "Авто";
        };
    }
}
