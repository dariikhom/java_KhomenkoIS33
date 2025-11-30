package lab6;

// клас тариф, що зберігає інформацію про конкретний тариф мобільної компанії
public class Tariff {

    protected String name;  // назва тарифу

    protected double monthlyFee;  // місячна плата 
 
    protected int subscribers;  // кількість клієнтів

    // конструктор класу, який ініціалізує всі поля
    public Tariff(String name, double monthlyFee, int subscribers) {
        this.name = name;             
        this.monthlyFee = monthlyFee; 
        this.subscribers = subscribers; 
    }

    // метод для отримання абонентської плати
    public double getMonthlyFee() {
        return monthlyFee;
    }

    // метод для отримання кількості клієнтів тарифу
    public int getSubscribers() {
        return subscribers; 
    }

    // метод для виводу інформації про тариф
    @Override
    public String toString() {
        return name + " (price: " + monthlyFee + " grn, subscribers: " + subscribers + ")";
    }

    // метод для порівняння двох тарифів
    @Override
    public boolean equals(Object obj) {
        // перевіряємо, чи об'єкт є тарифом
        if (!(obj instanceof Tariff t)) return false;

        // порівнюємо назву, плату і кількість клієнтів
        return name.equals(t.name) &&
                monthlyFee == t.monthlyFee &&
                subscribers == t.subscribers;
    }

    // метод для отримання хеш-коду об'єкта
    @Override
    public int hashCode() {
        // обчислюємо
        return name.hashCode() + (int) monthlyFee * 31 + subscribers * 7;
    }
}
