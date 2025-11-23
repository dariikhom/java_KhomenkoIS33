/* c13 = 23/13 = 10
Визначити ієрархію тарифів мобільної компанії. Створити список тарифів компанії. 
Порахувати загальну чисельність клієнтів. 
Провести сортування тарифів на основі розміру абонентської плати. 
Знайти тариф у компанії, що відповідає заданому діапазону вартості послуг.*/
import java.util.*;

// узагальнений клас тариф
class Tariff {
    
    protected String name;  // назва тарифу
    protected double monthlyFee;  // абонентська плата в грн
    protected int subscribers;  // кількість клієнтів на тарифі

    // конструктор тарифу
    public Tariff(String name, double monthlyFee, int subscribers) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.subscribers = subscribers;
    }

    // геттер для абонентської плати
    public double getMonthlyFee() {
        return monthlyFee;
    }

    // геттер для кількості клієнтів
    public int getSubscribers() {
        return subscribers;
    }

    // toString для зручного виводу
    @Override
    public String toString() {
        return name + " (price: " + monthlyFee + " grn, subscribers: " + subscribers + ")";
    }
}

// нащадок тарифу для базового пакету
class BasicTariff extends Tariff {
    public BasicTariff(String name, double monthlyFee, int subscribers) {
        super(name, monthlyFee, subscribers);
    }
}

// нащадок тарифу для преміум пакету
class PremiumTariff extends Tariff {
    public PremiumTariff(String name, double monthlyFee, int subscribers) {
        super(name, monthlyFee, subscribers);
    }
}

// нащадок тарифу для бізнес пакету
class BusinessTariff extends Tariff {
    public BusinessTariff(String name, double monthlyFee, int subscribers) {
        super(name, monthlyFee, subscribers);
    }
}

// клас мобільної компанії, що містить масив тарифів
class MobileCompany {
    private List<Tariff> tariffs;

    // конструктор створює порожній список тарифів
    public MobileCompany() {
        tariffs = new ArrayList<>();
    }

    // метод для додавання тарифу
    public void addTariff(Tariff t) {
        if (t == null) {
            throw new IllegalArgumentException("tariff не може бути null");
        }
        tariffs.add(t);
    }

    // метод для підрахунку загальної кількості клієнтів
    public int getTotalSubscribers() {
        int total = 0;
        for (Tariff t : tariffs) {
            total += t.getSubscribers();
        }
        return total;
    }

    // метод для сортування тарифів за абонентською платою (зростання)
    public void sortByFeeAscending() {
        tariffs.sort(Comparator.comparingDouble(Tariff::getMonthlyFee));
    }

    // метод для пошуку тарифів у заданому діапазоні абонентської плати
    public List<Tariff> findTariffsInRange(double minFee, double maxFee) {
        List<Tariff> result = new ArrayList<>();
        for (Tariff t : tariffs) {
            if (t.getMonthlyFee() >= minFee && t.getMonthlyFee() <= maxFee) {
                result.add(t);
            }
        }
        return result;
    }

    // метод для виводу всіх тарифів
    public void printTariffs() {
        for (Tariff t : tariffs) {
            System.out.println(t);
        }
    }
}

// головний клас програми
public class lab5 {
    public static void main(String[] args) {
        try {
            // створюємо мобільну компанію
            MobileCompany company = new MobileCompany();

            // додаємо тарифи
            company.addTariff(new BasicTariff("Basic 100", 100, 1200));
            company.addTariff(new PremiumTariff("Premium 300", 300, 800));
            company.addTariff(new BusinessTariff("Business 500", 500, 300));
            company.addTariff(new BasicTariff("Basic 150", 150, 1000));
            company.addTariff(new PremiumTariff("Premium 350", 350, 600));

            // виводимо всі тарифи
            System.out.println("All company tariffs:");
            company.printTariffs();

            // підрахунок загальної кількості клієнтів
            System.out.println("\nTotal number of customers: " + company.getTotalSubscribers());

            // сортування тарифів за абонентською платою
            company.sortByFeeAscending();
            System.out.println("\nTariffs after price sort:");
            company.printTariffs();

            // пошук тарифів у діапазоні 150-350 грн
            double minFee = 150;
            double maxFee = 350;
            List<Tariff> foundTariffs = company.findTariffsInRange(minFee, maxFee);
            System.out.println("\nTariffs in range " + minFee + " -- " + maxFee + " grn:");
            if (foundTariffs.isEmpty()) {
                System.out.println("No tariffs in this range");
            } else {
                for (Tariff t : foundTariffs) {
                    System.out.println(t);
                }
            }

        } catch (Exception e) {
            // обробка всіх винятків
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
