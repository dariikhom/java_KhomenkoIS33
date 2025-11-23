// c11 = 23/11 = 1
// Визначити клас автомобіль, який складається як мінімум з 5-и полів

class Car {

    // поля класу
    private String brand; // марка
    private String model; // модель
    private int year; // рік випуску
    private double engineVolume; // об'єм двигуна
    private double price; // ціна автомобіля

    // конструктор класу
    public Car(String brand, String model, int year, double engineVolume, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineVolume = engineVolume;
        this.price = price;
    }

    // геттери
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public double getPrice() {
        return price;
    }

    // метод для порівняння автомобілів
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Car))
            return false;

        Car other = (Car) obj;
        return brand.equals(other.brand)
                && model.equals(other.model)
                && year == other.year
                && Double.compare(engineVolume, other.engineVolume) == 0
                && Double.compare(price, other.price) == 0;
    }

    // вивід інформації про автомобіль
    @Override
    public String toString() {
        return brand + " " + model + ", " + year +
                ", " + engineVolume + "L, $" + price;
    }
}



// головний клас програми
public class lab3 {

    public static void main(String[] args)  throws Exception {
        
        // створення масиву автомобілів
        Car[] cars = {
                new Car("Toyota", "Camry", 2018, 2.5, 18000),
                new Car("BMW", "X5", 2020, 3.0, 55000),
                new Car("Nisan", "Aria", 2020, 1.8, 25000),
                new Car("Audi", "A6", 2019, 2.0, 42000),
                new Car("Ford", "Focus", 2016, 1.6, 12000)
        };

        // вивід початкового масиву
        System.out.println("Initial array"); //початковий масив
        for (Car c : cars) {
            System.out.println(c);
        }

        // cортування за роком випуску (за зростанням)
        java.util.Arrays.sort(cars, java.util.Comparator.comparingInt(Car::getYear));

        System.out.println("\nSort by year (increasing)"); 
        for (Car c : cars) {
            System.out.println(c);
        }

        // сортування за ціною (за спаданням)
        java.util.Arrays.sort(cars,
                java.util.Comparator.comparingDouble(Car::getPrice).reversed());

        System.out.println("\nSort by price (decreasing)");
        for (Car c : cars) {
            System.out.println(c);
        }

        // об'єкт для пошуку
        Car target = new Car("Toyota", "Camry", 2018, 2.5, 18000);
        Car target1 = new Car("Toyota", "Corolla", 2018, 2.5, 18000);

        // пошук об'єкта в масиві
        System.out.println("\nObject search: " + target);
        boolean found = false;
        for (Car c : cars) {
            if (c.equals(target)) {
                System.out.println("Object found");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Object is NOT found.");
        }

        // пошук об'єкта target1 в масиві
        System.out.println("\nObject search: " + target1);
        found = false;
        for (Car c : cars) {
            if (c.equals(target1)) {
                System.out.println("Object found: " + c);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Object is NOT found.");
        }
    }
}
