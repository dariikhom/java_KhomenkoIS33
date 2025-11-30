package lab6;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // створюємо власну колекцію тарифів
        CustomSet<Tariff> tariffs = new CustomSet<>();

        tariffs.add(new Tariff("Basic 100", 100, 1200));
        tariffs.add(new Tariff("Premium 300", 300, 800));
        tariffs.add(new Tariff("Business 500", 500, 300));
        tariffs.add(new Tariff("Basic 150", 150, 1000));
        tariffs.add(new Tariff("Premium 350", 350, 600));

        System.out.println("Tariffs in set");
        for (Tariff t : tariffs) {
            System.out.println(t);
        }

        // підрахунок загальної кількості
        int total = 0;
        for (Tariff t : tariffs) {
            total += t.getSubscribers();
        }
        System.out.println("\nTotal subscribers = " + total);

        // сортування через копію
        List<Tariff> sorted = new ArrayList<>();
        sorted.addAll(tariffs);
        sorted.sort(Comparator.comparingDouble(Tariff::getMonthlyFee));

        System.out.println("\nSorted tariffs");
        sorted.forEach(System.out::println);

        // знайти тарифи в діапазоні
        double min = 150, max = 350;

        System.out.println("\nTariffs in range " + min + " - " + max );
        for (Tariff t : tariffs) {
            if (t.getMonthlyFee() >= min && t.getMonthlyFee() <= max) {
                System.out.println(t);
            }
        }
    }
}
