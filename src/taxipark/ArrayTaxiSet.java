package taxipark;

import java.util.*;

/**
 * Власна типізована колекція-множина на основі масиву,
 * що реалізує інтерфейс Set.
 * <p>
 * Колекція зберігає лише унікальні посилання на об'єкти типу {@code T extends Car}.
 * Внутрішня структура – масив із початковою місткістю 15 елементів.
 * Якщо під час додавання елементів місця не вистачає, місткість
 * збільшується на 30% від поточного розміру.
 */
public class ArrayTaxiSet<T extends Car> implements Set<T> {
    /**
     * Початкова місткість внутрішнього масиву.
     */
    private static final int DEFAULT_CAPACITY = 15;
    private T[] elements;
    private int size = 0;

    /**
     * Порожній конструктор.
     * <p>
     * Створює порожню множину з початковою місткістю #DEFAULT_CAPACITY елементів.
     */
    @SuppressWarnings("unchecked")
    public ArrayTaxiSet() {
        elements = (T[]) new Car[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, що створює множину з одним елементом.
     * @param singleElement елемент, який буде доданий до множини
     */
    public ArrayTaxiSet(T singleElement) {
        this();
        add(singleElement);
    }

    /**
     * Конструктор, що створює множину з елементів стандартної колекції.
     * Дублікати не додаються (відповідно до семантики Set).
     * @param collection вихідна колекція елементів
     */
    public ArrayTaxiSet(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    /**
     * Забезпечує достатню місткість внутрішнього масиву для збереження
     * щонайменше {@code minCapacity} елементів.
     * <p>
     * Якщо поточна місткість менша за потрібну, вона збільшується
     * приблизно на 30% від поточного розміру.
     */
    private void ensureCapacity(int minCapacity) {
        if (elements.length >= minCapacity) return;

        int oldCapacity = elements.length;
        int grow = (int) (oldCapacity * 0.3);

        if (grow < 1) grow = 1;

        int newCapacity = oldCapacity + grow;

        if (newCapacity < minCapacity)
            newCapacity = minCapacity;

        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Повертає індекс першого входження об'єкта в масив, або -1, якщо елемент не знайдено.
     */
    private int indexOf(Object o) {
        if (o == null) return -1;
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Видаляє елемент за заданим індексом із внутрішнього масиву зі зсувом усіх наступних елементів вліво.
     */
    private void removeAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     * Повертає кількість елементів у множині.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Повертає true, якщо множина порожня.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Перевіряє, чи містить множина переданий елемент.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Створює та повертає новий ітератор для обходу множини.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayTaxiSetIterator();
    }

    /**
     * Повертає масив об'єктів, що містяться у множині.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Копіює елементи множини у переданий масив або створює новий, якщо переданий замалий.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Додає елемент до множини, якщо він ще не присутній.
     */
    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Елемент не може бути null");
        }
        if (contains(t)) {
            return false;
        }
        ensureCapacity(size + 1);
        elements[size++] = t;
        return true;
    }

    /**
     * Видаляє елемент з множини, якщо він присутній.
     */
    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx < 0) return false;
        removeAt(idx);
        return true;
    }

    /**
     * Перевіряє, чи містить множина всі елементи переданої колекції.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    /**
     * Додає усі елементи колекції до множини (без дублювання).
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T item : c) {
            if (add(item)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Видаляє всі елементи множини, яких немає у переданій колекції.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                removeAt(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Видаляє всі елементи множини, які присутні у переданій колекції.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (true) {
                int idx = indexOf(o);
                if (idx < 0) break;
                removeAt(idx);
                modified = true;
            }
        }
        return modified;
    }


    /**
     * Очищає множину, видаляючи всі елементи.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Внутрішній ітератор для обходу елементів {@link ArrayTaxiSet}.
     */
    private class ArrayTaxiSetIterator implements Iterator<T> {
        private int cursor = 0;
        private int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            return elements[cursor++];
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException("next() не був викликаний або елемент вже видалений");
            }
            ArrayTaxiSet.this.removeAt(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
        }
    }
}
