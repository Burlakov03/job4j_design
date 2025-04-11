package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        Iterator<T> iterator = iterator();
        boolean result = true;
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                result = false;
                break;
            }
        }
        if (result) {
            set.add(value);
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iterator = iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}