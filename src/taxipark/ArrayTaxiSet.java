package taxipark;

import java.util.*;

public class ArrayTaxiSet<T extends Car> implements Set<T> {
    private static final int DEFAULT_CAPACITY = 15;
    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayTaxiSet() {
        elements = (T[]) new Car[DEFAULT_CAPACITY];
    }

    public ArrayTaxiSet(T singleElement) {
        this();
        add(singleElement);
    }

    public ArrayTaxiSet(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

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


    private int indexOf(Object o) {
        if (o == null) return -1;
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void removeAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayTaxiSetIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

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

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx < 0) return false;
        removeAt(idx);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

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

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


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
