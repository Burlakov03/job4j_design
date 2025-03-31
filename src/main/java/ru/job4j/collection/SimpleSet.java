package ru.job4j.collection;


import java.util.Arrays;
import java.util.Objects;

public class SimpleSet {
    private String[] container = new String[2];
    private int size;

    public boolean add(String values) {
        if (size == container.length) {
            grow();
        }
        boolean result = !contains(values);
        if (result) {
            container[size++] = values;
        }
        return result;
    }

    private boolean contains(String values) {
        boolean result = false;
        for (String element : container) {
            if (Objects.equals(element, values)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }
}
