package lab6;

import java.util.*;

// колекції на основі двозв’язного списку,
// реалізує інтерфейс set<t>
public class CustomSet<T> implements Set<T> {

    // двозв’язний список
    private class Node {
        T data;    // значення вузла
        Node prev; // попередній вузол
        Node next; // наступний вузол

        // конструктор вузла
        Node(T data) {
            this.data = data; 
        }
    }

    private Node head; // перший вузол 
    private Node tail; // останній вузол 
    private int size = 0; // кількість елементів у списку

    // конструктор, який створює пусту колекцію
    public CustomSet() {}

    // конструктор з одним елементом
    public CustomSet(T element) {
        add(element); // додаємо елемент у колекцію
    }

    // конструктор зі стандартної колекції
    public CustomSet(Collection<T> collection) {
        addAll(collection); // додаємо всі елементи стандартної колекції
    }

    // перевірка чи містить колекція певний елемент
    private boolean containsNode(T element) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) return true; 
            current = current.next; 
        }
        return false; 
    }

    // додавання нового вузла в кінець списку
    private void appendNode(T element) {
        Node n = new Node(element); // створюємо новий вузол
        if (head == null) { 
            head = tail = n;        // якщо список порожній, встановлюємо вузол як head і tail
        } else {
            n.prev = tail;          
            tail.next = n;          
            tail = n;               // якщо список не порожній, tail оновлюємо на новий вузол
        }
        size++;                     // збільшуємо розмір колекції
    }

    // реалізація інтерфейсу set<t>

    @Override
    public int size() { return size; } // повертає кількість елементів

    @Override
    public boolean isEmpty() { return size == 0; } // перевірка чи колекція порожня

    @Override
    public boolean contains(Object o) {
        return containsNode((T) o); // перевірка наявності елемента
    }

    @Override
    public Iterator<T> iterator() {
        // створюємо ітератор для проходу по колекції
        return new Iterator<T>() {
            Node current = head; // починаємо з голови списку

            @Override
            public boolean hasNext() { return current != null; } // чи є наступний елемент

            @Override
            public T next() { // повертаємо дані поточного вузла і рухаємося далі
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public boolean add(T element) {
        if (contains(element)) return false; // якщо елемент вже є, не додаємо
        appendNode(element);                 // додаємо елемент у список
        return true;                        
    }

    @Override
    public boolean remove(Object o) {
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(o)) {  // знайшли елемент для видалення

                if (curr.prev != null) curr.prev.next = curr.next;  // оновлюємо prev вузла
                else head = curr.next;  // якщо це перший елемент, оновлюємо head

                if (curr.next != null) curr.next.prev = curr.prev;  // оновлюємо next вузла
                else tail = curr.prev;  // якщо це останній елемент, оновлюємо tail
  
                size--;  // зменшуємо розмір
                return true; 
            }
            curr = curr.next;
        }
        return false;  
    }

    @Override
    public void clear() {
        head = tail = null;  // очищаємо
        size = 0;         
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T el : c) changed |= add(el); // додаємо всі елементи
        return changed;        
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el : c) if (!contains(el)) return false; // якщо хоча б одного немає то false
        return true;   
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object el : c) changed |= remove(el);  // видаляємо всі елементи, якщо вони є
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;         // зберігаємо наступний вузол
            if (!c.contains(curr.data)) {    
                remove(curr.data);         // якщо елемента немає в колекції c видаляємо його
                changed = true;            // колекція змінилася
            }
            curr = next;                   // переходимо до наступного вузла
        }
        return changed;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];  // створюємо масив розміром size
        int i = 0;
        for (T el : this) arr[i++] = el;  // копіюємо всі елементи у масив
        return arr;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        int i = 0;
        for (T el : this) a[i++] = (E) el;  // копіюємо всі елементи у переданий масив
        return a;
    }
}
